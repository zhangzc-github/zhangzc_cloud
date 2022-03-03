package com.zhangzc.cloud.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.plugin.excel.vo.ErrorMessage;
import com.zhangzc.cloud.admin.mapper.SysDeptMapper;
import com.zhangzc.cloud.admin.mapper.SysRoleMapper;
import com.zhangzc.cloud.admin.mapper.SysUserMapper;
import com.zhangzc.cloud.admin.mapper.SysUserRoleMapper;
import com.zhangzc.cloud.admin.service.SysMenuService;
import com.zhangzc.cloud.admin.service.SysUserService;
import com.zhangzc.cloud.common.core.constant.CacheConstants;
import com.zhangzc.cloud.common.core.constant.CommonConstants;
import com.zhangzc.cloud.common.core.constant.enums.MenuTypeEnum;
import com.zhangzc.cloud.common.core.util.R;
import com.zhangzc.cloud.upms.api.dto.UserDTO;
import com.zhangzc.cloud.upms.api.dto.UserInfo;
import com.zhangzc.cloud.upms.api.entity.*;
import com.zhangzc.cloud.upms.api.vo.UserExcelVO;
import com.zhangzc.cloud.upms.api.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * SysUserServiceImpl
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/2/14 10:59 上午
 */
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();
    private final SysRoleMapper sysRoleMapper;
    private final SysMenuService sysMenuService;
    private final SysUserRoleMapper sysUserRoleMapper;
    private final SysDeptMapper sysDeptMapper;

    /**
     * 查询用户信息
     * @param sysUser user
     * @return userInfo
     */
    @Override
    public UserInfo getUserInfo(SysUser sysUser) {
        UserInfo userInfo = new UserInfo();
        userInfo.setSysUser(sysUser);
        //设置角色列表
        List<SysRole> roleList = sysRoleMapper.listRolesByUserId(sysUser.getUserId());
        userInfo.setRoleList(roleList);
        List<Long> roleIds = roleList.stream().map(SysRole::getRoleId).collect(Collectors.toList());
        userInfo.setRoles(ArrayUtil.toArray(roleIds, Long.class));
        //设置权限列表
        Set<String> permission = roleIds.stream().map(sysMenuService::findMenuByRoleId).flatMap(Collection::stream)
                .filter(m -> MenuTypeEnum.BUTTON.getType().equals(m.getType())).map(SysMenu::getPermission)
                .filter(StrUtil::isNotBlank).collect(Collectors.toSet());
        userInfo.setPermissions(ArrayUtil.toArray(permission, String.class));
        return userInfo;
    }

    /**
     * 分页查询用户信息（含有角色信息）
     * @param page 分页对象
     * @param userDTO 参数列表
     * @return page
     */
    @Override
    public IPage<List<UserVO>> getUserWithRolePage(Page page, UserDTO userDTO) {
        return baseMapper.getUserVosPage(page, userDTO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = CacheConstants.USER_DETAILS, key = "#sysUser.username")
    public Boolean removeUserById(SysUser sysUser) {
        sysUserRoleMapper.deleteByUserId(sysUser.getUserId());
        this.removeById(sysUser.getUserId());
        return Boolean.TRUE;
    }

    /**
     * 更新当前用户信息
     * @param userDto 用户信息
     * @return boolean
     */
    @Override
    @CacheEvict(value = CacheConstants.USER_DETAILS, key = "#userDto.username")
    public Boolean updateUserInfo(UserDTO userDto) {
        UserVO userVO = baseMapper.getUserVoByUsername(userDto.getUsername());

        Assert.isTrue(ENCODER.matches(userDto.getPassword(),userVO.getPassword()), "原密码错误，修改失败");

        SysUser sysUser = new SysUser();
        if (StrUtil.isNotBlank(userDto.getNewpassword1())) {
            sysUser.setPassword(ENCODER.encode(userDto.getNewpassword1()));
        }
        sysUser.setPhone(userDto.getPhone());
        sysUser.setUserId(userVO.getUserId());
        sysUser.setAvatar(userDto.getAvatar());
        return this.updateById(sysUser);
    }

    /**
     * 更新指定用户信息
     * @param userDto 用户信息
     * @return boolean
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = CacheConstants.USER_DETAILS, key = "#userDto.username")
    public Boolean updateUser(UserDTO userDto) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(userDto, sysUser);
        sysUser.setUpdateTime(LocalDateTime.now());

        if (StrUtil.isNotBlank(userDto.getPassword())) {
            sysUser.setPassword(ENCODER.encode(userDto.getPassword()));
        }
        this.updateById(sysUser);

        sysUserRoleMapper
                .delete(Wrappers.<SysUserRole>update().lambda().eq(SysUserRole::getUserId, userDto.getUserId()));
        userDto.getRole().forEach(roleId -> {
            SysUserRole userRole = new SysUserRole();
            userRole.setUserId(sysUser.getUserId());
            userRole.setRoleId(roleId);
            userRole.insert();
        });
        return Boolean.TRUE;
    }

    @Override
    public UserVO getUserVoById(Long id) {
        return baseMapper.getUserVoById(id);
    }

    /**
     * 查询上级部门的用户信息
     * @param username 用户名
     * @return R
     */
    @Override
    public List<SysUser> listAncestorUsersByUsername(String username) {
        SysUser sysUser = this.getOne(Wrappers.<SysUser>query().lambda().eq(SysUser::getUsername, username));

        SysDept sysDept = sysDeptMapper.selectById(sysUser.getDeptId());
        if (sysDept == null) {
            return null;
        }

        Long parentId = sysDept.getParentId();
        return this.list(Wrappers.<SysUser>query().lambda().eq(SysUser::getDeptId, parentId));
    }

    /**
     * 保存用户信息
     * @param userDto DTO 对象
     * @return success/fail
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveUser(UserDTO userDto) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(userDto, sysUser);
        sysUser.setDelFlag(CommonConstants.STATUS_NORMAL);
        sysUser.setPassword(ENCODER.encode(userDto.getPassword()));
        baseMapper.insert(sysUser);
        userDto.getRole().stream().map(roleId -> {
            SysUserRole userRole = new SysUserRole();
            userRole.setUserId(sysUser.getUserId());
            userRole.setRoleId(roleId);
            return userRole;
        }).forEach(sysUserRoleMapper::insert);
        return Boolean.TRUE;
    }

    @Override
    public List<Long> listUserIdByDeptIds(Set<Long> deptIds) {
        return this.listObjs(Wrappers.lambdaQuery(SysUser.class).select(SysUser::getUserId).in(SysUser::getDeptId, deptIds),
                Long.class::cast);
    }

    /**
     * 查询全部的用户
     * @param userDTO 查询条件
     * @return list
     */
    @Override
    public List<UserExcelVO> listUser(UserDTO userDTO) {
        List<UserVO> voList = baseMapper.selectVoList(userDTO);
        // 转换成execl 对象输出
        List<UserExcelVO> userExcelVOList = voList.stream().map(userVO -> {
            UserExcelVO excelVO = new UserExcelVO();
            BeanUtils.copyProperties(userVO, excelVO);
            String roleNameList = userVO.getRoleList().stream().map(SysRole::getRoleName)
                    .collect(Collectors.joining(StrUtil.COMMA));
            excelVO.setRoleNameList(roleNameList);
            return excelVO;
        }).collect(Collectors.toList());
        return userExcelVOList;
    }

    /**
     * excel 导入用户, 插入正确的 错误的提示行号
     * @param excelVOList excel 列表数据
     * @param bindingResult 错误数据
     * @return ok fail
     */
    @Override
    public R importUser(List<UserExcelVO> excelVOList, BindingResult bindingResult) {
        // 通用校验获取失败的数据
        List<ErrorMessage> errorMessageList = (List<ErrorMessage>) bindingResult.getTarget();

        // 个性化校验逻辑
        List<SysUser> userList = this.list();
        List<SysDept> deptList = sysDeptMapper.selectList(Wrappers.emptyWrapper());
        List<SysRole> roleList = sysRoleMapper.selectList(Wrappers.emptyWrapper());

        // 执行数据插入操作 组装 UserDto
        for (int i = 0; i < excelVOList.size(); i++) {
            UserExcelVO excel = excelVOList.get(i);
            Set<String> errorMsg = new HashSet<>();
            // 校验用户名是否存在
            boolean exsitUserName = userList.stream()
                    .anyMatch(sysUser -> excel.getUsername().equals(sysUser.getUsername()));

            if (exsitUserName) {
                errorMsg.add(String.format("%s 用户名已存在", excel.getUsername()));
            }

            // 判断输入的部门名称列表是否合法
            Optional<SysDept> deptOptional = deptList.stream()
                    .filter(dept -> excel.getDeptName().equals(dept.getName())).findFirst();
            if (!deptOptional.isPresent()) {
                errorMsg.add(String.format("%s 部门名称不存在", excel.getDeptName()));
            }

            // 判断输入的角色名称列表是否合法
            List<String> roleNameList = StrUtil.split(excel.getRoleNameList(), StrUtil.COMMA);
            List<SysRole> roleCollList = roleList.stream()
                    .filter(role -> roleNameList.stream().anyMatch(name -> role.getRoleName().equals(name)))
                    .collect(Collectors.toList());

            if (roleCollList.size() != roleNameList.size()) {
                errorMsg.add(String.format("%s 角色名称不存在", excel.getRoleNameList()));
            }

            // 数据合法情况
            if (CollUtil.isEmpty(errorMsg)) {
                insertExcelUser(excel, deptOptional, roleCollList);
            }
            else {
                // 数据不合法情况
                errorMessageList.add(new ErrorMessage((long) (i + 2), errorMsg));
            }

        }

        if (CollUtil.isNotEmpty(errorMessageList)) {
            return R.failed(errorMessageList);
        }
        return R.ok();
    }

    /**
     * 插入excel User
     */
    private void insertExcelUser(UserExcelVO excel, Optional<SysDept> deptOptional, List<SysRole> roleCollList) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(excel.getUsername());
        userDTO.setPhone(excel.getPhone());
        // 批量导入初始密码为手机号
        userDTO.setPassword(userDTO.getPhone());
        // 根据部门名称查询部门ID
        userDTO.setDeptId(deptOptional.get().getDeptId());
        // 根据角色名称查询角色ID
        List<Long> roleIdList = roleCollList.stream().map(SysRole::getRoleId).collect(Collectors.toList());
        userDTO.setRole(roleIdList);
        // 插入用户
        this.saveUser(userDTO);
    }
}
