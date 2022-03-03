package com.zhangzc.cloud.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangzc.cloud.admin.mapper.SysMenuMapper;
import com.zhangzc.cloud.admin.mapper.SysRoleMenuMapper;
import com.zhangzc.cloud.admin.service.SysMenuService;
import com.zhangzc.cloud.common.core.constant.CacheConstants;
import com.zhangzc.cloud.common.core.constant.CommonConstants;
import com.zhangzc.cloud.common.core.constant.enums.MenuTypeEnum;
import com.zhangzc.cloud.upms.api.entity.SysMenu;
import com.zhangzc.cloud.upms.api.entity.SysRoleMenu;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * SysMenuServiceImpl
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/2/14 11:16 上午
 */
@Service
@RequiredArgsConstructor
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {
    private final SysRoleMenuMapper sysRoleMenuMapper;
    /**
     * 通过roleId查询菜单
     * @param roleId role id
     * @return Set<SysMenu>
     */
    @Override
    @Cacheable(value = CacheConstants.MENU_DETAILS, key = "#roleId  + '_menu'", unless = "#result == null")
    public Set<SysMenu> findMenuByRoleId(long roleId) {
        return baseMapper.listMenusByRoleId(roleId);
    }

    /**
     * 级联删除菜单
     * @param id 菜单ID
     * @return true成功, false失败
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = CacheConstants.MENU_DETAILS, allEntries = true)
    public Boolean removeMenuById(Long id) {
        List<SysMenu> menuList = this.list(Wrappers.<SysMenu>query().lambda().eq(SysMenu::getParentId, id));

        Assert.isTrue(CollUtil.isEmpty(menuList), "菜单含有下级不能删除");

        sysRoleMenuMapper.delete(Wrappers.<SysRoleMenu>query().lambda().eq(SysRoleMenu::getMenuId, id));
        return this.removeById(id);
    }

    /**
     * 更新菜单信息
     * @param sysMenu 菜单信息
     * @return 成功、失败
     */
    @Override
    @CacheEvict(value = CacheConstants.MENU_DETAILS, allEntries = true)
    public Boolean updateMenuById(SysMenu sysMenu) {
        return this.updateMenuById(sysMenu);
    }

    /**
     * 构建树查询 1. 不是懒加载情况，查询全部 2. 是懒加载，根据parentId 查询 2.1 父节点为空，则查询ID -1
     * @param lazy 是否是懒加载
     * @param parentId 父节点ID
     * @return list
     */
    @Override
    public List<Tree<Long>> treeMenu(boolean lazy, Long parentId) {
        if (!lazy) {
            List<TreeNode<Long>> collect = baseMapper.selectList(Wrappers.<SysMenu>lambdaQuery().orderByAsc(SysMenu::getSortOrder))
                    .stream().map(getNodeFunction()).collect(Collectors.toList());
            return TreeUtil.build(collect, CommonConstants.MENU_TREE_ROOT_ID);
        }

        Long parent = parentId == null ? CommonConstants.MENU_TREE_ROOT_ID : parentId;

        List<TreeNode<Long>> collect = baseMapper.selectList(Wrappers.<SysMenu>lambdaQuery().eq(SysMenu::getParentId, parent).orderByAsc(SysMenu::getSortOrder))
                .stream().map(getNodeFunction()).collect(Collectors.toList());
        return TreeUtil.build(collect, CommonConstants.MENU_TREE_ROOT_ID);
    }

    /**
     * 查询菜单
     * @param menuSet 全部菜单
     * @param parentId 父节点ID
     * @return
     */
    @Override
    public List<Tree<Long>> filterMenu(Set<SysMenu> menuSet, Long parentId) {
        List<TreeNode<Long>> collect = menuSet.stream()
                .filter(menu -> MenuTypeEnum.LEFT_MENU.getType().equals(menu.getType()))
                .filter(menu -> StrUtil.isNotBlank(menu.getPath()))
                .map(getNodeFunction())
                .collect(Collectors.toList());
        Long parent = parentId == null ? CommonConstants.MENU_TREE_ROOT_ID : parentId;
        return TreeUtil.build(collect, parent);
    }

    @Override
    @CacheEvict(value = CacheConstants.MENU_DETAILS, allEntries = true)
    public void clearMenuCache() {

    }

    @NotNull
    private Function<SysMenu, TreeNode<Long>> getNodeFunction() {
        return menu -> {
            TreeNode<Long> node = new TreeNode<>();
            node.setId(menu.getMenuId());
            node.setName(menu.getName());
            node.setParentId(menu.getParentId());
            node.setWeight(menu.getSortOrder());
            // 扩展属性
            Map<String, Object> extra = new HashMap<>();
            extra.put("icon", menu.getIcon());
            extra.put("path", menu.getPath());
            extra.put("type", menu.getType());
            extra.put("permission", menu.getPermission());
            extra.put("label", menu.getName());
            extra.put("sortOrder", menu.getSortOrder());
            extra.put("keepAlive", menu.getKeepAlive());
            node.setExtra(extra);
            return node;
        };
    }
}
