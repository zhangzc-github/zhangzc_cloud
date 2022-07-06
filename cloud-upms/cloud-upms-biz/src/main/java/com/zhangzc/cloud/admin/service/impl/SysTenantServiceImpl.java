package com.zhangzc.cloud.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangzc.cloud.admin.mapper.SysRoleMenuMapper;
import com.zhangzc.cloud.admin.mapper.SysTenantMapper;
import com.zhangzc.cloud.admin.service.*;
import com.zhangzc.cloud.common.core.constant.CacheConstants;
import com.zhangzc.cloud.common.core.constant.CommonConstants;
import com.zhangzc.cloud.common.data.resolver.ParamResolver;
import com.zhangzc.cloud.common.data.tenant.TenantBroker;
import com.zhangzc.cloud.upms.api.entity.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SysTenantServiceImpl extends ServiceImpl<SysTenantMapper, SysTenant> implements SysTenantService {

	private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();

	private final SysOauthClientDetailsService clientServices;

	private final SysDeptRelationService deptRelationService;

	private final SysUserRoleService userRoleService;

	private final SysRoleMenuMapper roleMenuMapper;

	private final SysDictItemService dictItemService;

	private final SysPublicParamService paramService;

	private final SysUserService userService;

	private final SysRoleService roleService;

	private final SysMenuService menuService;

	private final SysDeptService deptService;

	private final SysDictService dictService;

	/**
	 * 获取正常状态租户
	 * <p>
	 * 1. 状态正常 2. 开始时间小于等于当前时间 3. 结束时间大于等于当前时间
	 * @return
	 */
	@Override
	public List<SysTenant> getNormalTenant() {
		return baseMapper
				.selectList(Wrappers.<SysTenant>lambdaQuery().eq(SysTenant::getStatus, CommonConstants.STATUS_NORMAL));
	}

	/**
	 * 保存租户
	 * <p>
	 * 1. 保存租户 2. 初始化权限相关表 - sys_user - sys_role - sys_menu - sys_user_role -
	 * sys_role_menu - sys_dict - sys_dict_item - sys_client_details - sys_public_params
	 * @param sysTenant 租户实体
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	@CacheEvict(value = CacheConstants.TENANT_DETAILS)
	public Boolean saveTenant(SysTenant sysTenant) {
		this.save(sysTenant);
		// 查询系统默认租户配置参数
		Long defaultId = ParamResolver.getLong("TENANT_DEFAULT_ID", 1L);
		String defaultDeptName = ParamResolver.getStr("TENANT_DEFAULT_DEPTNAME", "租户默认部门");
		String defaultUsername = ParamResolver.getStr("TENANT_DEFAULT_USERNAME", "admin");
		String defaultPassword = ParamResolver.getStr("TENANT_DEFAULT_PASSWORD", "123456");
		String defaultRoleCode = ParamResolver.getStr("TENANT_DEFAULT_ROLECODE", "ROLE_ADMIN");
		String defaultRoleName = ParamResolver.getStr("TENANT_DEFAULT_ROLENAME", "租户默认角色");

		List<SysDict> dictList = new ArrayList<>(32);
		List<Long> dictIdList = new ArrayList<>(32);
		List<SysDictItem> dictItemList = new ArrayList<>(64);
		List<Tree<Long>> menuTrees = new ArrayList<>(128);
		List<SysOauthClientDetails> clientDetailsList = new ArrayList<>(16);
		List<SysPublicParam> publicParamList = new ArrayList<>(64);

		TenantBroker.runAs(defaultId, (id) -> {
			// 查询系统内置字典
			dictList.addAll(dictService.list());
			// 查询系统内置字典项目
			dictIdList.addAll(dictList.stream().map(SysDict::getId).collect(Collectors.toList()));
			dictItemList.addAll(
					dictItemService.list(Wrappers.<SysDictItem>lambdaQuery().in(SysDictItem::getDictId, dictIdList)));
			// 查询当前租户菜单
			menuTrees.addAll(menuService.treeMenu(false, CommonConstants.MENU_TREE_ROOT_ID));
			// 查询客户端配置
			clientDetailsList.addAll(clientServices.list());
			// 查询系统参数配置
			publicParamList.addAll(paramService.list());
		});

		// 保证插入租户为新的租户
		return TenantBroker.applyAs(sysTenant.getId(), (id -> {

			// 插入部门
			SysDept dept = new SysDept();
			dept.setName(defaultDeptName);
			dept.setParentId(0L);
			deptService.save(dept);
			// 维护部门关系
			deptRelationService.saveDeptRelation(dept);
			// 构造初始化用户
			SysUser user = new SysUser();
			user.setUsername(defaultUsername);
			user.setPassword(ENCODER.encode(defaultPassword));
			user.setDeptId(dept.getDeptId());
			userService.save(user);
			// 构造新角色
			SysRole role = new SysRole();
			role.setRoleCode(defaultRoleCode);
			role.setRoleName(defaultRoleName);
			roleService.save(role);
			// 用户角色关系
			SysUserRole userRole = new SysUserRole();
			userRole.setUserId(user.getUserId());
			userRole.setRoleId(role.getRoleId());
			userRoleService.save(userRole);
			// 插入新的菜单
			saveTenantMenu(menuTrees, CommonConstants.MENU_TREE_ROOT_ID);
			List<SysMenu> newMenuList = menuService.list();

			// 查询全部菜单,构造角色菜单关系
			List<SysRoleMenu> roleMenuList = newMenuList.stream().map(menu -> {
				SysRoleMenu roleMenu = new SysRoleMenu();
				roleMenu.setRoleId(role.getRoleId());
				roleMenu.setMenuId(menu.getMenuId());
				return roleMenu;
			}).collect(Collectors.toList());
			roleMenuMapper.insertBatchSomeColumn(roleMenuList);
			// 插入系统字典
			dictService.saveBatch(dictList);
			// 处理字典项最新关联的字典ID
			List<SysDictItem> itemList = dictList.stream().flatMap(dict -> dictItemList.stream()
					.filter(item -> item.getType().equals(dict.getType())).peek(item -> item.setDictId(dict.getId())))
					.collect(Collectors.toList());

			// 插入客户端
			clientServices.saveBatch(clientDetailsList);
			// 插入系统配置
			paramService.saveBatch(publicParamList);
			return dictItemService.saveBatch(itemList);
		}));

	}

	/**
	 * 保存新的租户菜单，维护成新的菜单
	 * @param nodeList 节点树
	 */
	private void saveTenantMenu(List<Tree<Long>> nodeList, Long parentId) {
		for (Tree<Long> node : nodeList) {
			SysMenu menu = new SysMenu();
			menu.setParentId(parentId);
			menu.setSortOrder(Convert.toInt(node.get("sortOrder")));
			menu.setIcon(Convert.toStr(node.get("icon")));
			menu.setPath(Convert.toStr(node.get("path")));
			menu.setType(Convert.toStr(node.get("type")));
			menu.setPermission(Convert.toStr(node.get("permission")));
			menu.setKeepAlive(Convert.toStr(node.get("keepAlive")));
			menu.setName(Convert.toStr(node.get("label")));
			menuService.save(menu);
			if (!CollUtil.isEmpty(node.getChildren())) {
				saveTenantMenu(node.getChildren(), menu.getMenuId());
			}

		}
	}

}
