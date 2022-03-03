package com.zhangzc.cloud.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.plugin.excel.annotation.RequestExcel;
import com.pig4cloud.plugin.excel.annotation.ResponseExcel;
import com.zhangzc.cloud.admin.service.SysUserService;
import com.zhangzc.cloud.common.core.util.R;
import com.zhangzc.cloud.common.security.annotation.Inner;
import com.zhangzc.cloud.common.security.util.SecurityUtils;
import com.zhangzc.cloud.upms.api.dto.UserDTO;
import com.zhangzc.cloud.upms.api.dto.UserInfo;
import com.zhangzc.cloud.upms.api.entity.SysUser;
import com.zhangzc.cloud.upms.api.vo.UserExcelVO;
import com.zhangzc.cloud.upms.api.vo.UserInfoVO;
import com.zhangzc.cloud.upms.api.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

/**
 * UserController
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/2/14 11:13 上午
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final SysUserService sysUserService;

    /**
     * 获取指定用户全部信息
     * @param username 用户名
     * @return 用户信息
     */
    @Inner
    @GetMapping("/info/{username}")
    public R<UserInfo> userInfo(@PathVariable String username){
        SysUser sysUser = sysUserService.getOne(Wrappers.<SysUser>query().lambda().eq(SysUser::getUsername, username));
        if (sysUser == null) {
            return R.failed(String.format("用户信息为空 %s", username));
        }
        return R.ok(sysUserService.getUserInfo(sysUser));
    }

    /**
     * 根据部门id，查询对应的用户 id 集合
     * @param deptIds 部门id 集合
     * @return 用户 id 集合
     */
    @Inner
    @GetMapping("/ids")
    public R<List<Long>> listUserIdByDeptIds(@RequestParam("deptIds") Set<Long> deptIds) {
        return R.ok(sysUserService.listUserIdByDeptIds(deptIds));
    }

    /**
     * 获取当前用户全部信息
     * @return 用户信息
     */
    @GetMapping("/info")
    public R<UserInfoVO> userInfo(){
        String username = SecurityUtils.getUser().getUsername();
        SysUser user = sysUserService.getOne(Wrappers.<SysUser>query().lambda().eq(SysUser::getUsername, username));
        if (user == null) {
            return R.failed("获取当前用户信息失败");
        }
        UserInfo userInfo = sysUserService.getUserInfo(user);
        UserInfoVO vo = new UserInfoVO();
        vo.setSysUser(user);
        vo.setPermissions(userInfo.getPermissions());
        vo.setRoles(userInfo.getRoles());
        return R.ok(vo);
    }

    /**
     * 通过ID查询用户信息
     * @param id ID
     * @return 用户信息
     */
    @GetMapping("/{id:\\d+}")
    public R<UserVO> user(@PathVariable Long id) {
        return R.ok(sysUserService.getUserVoById(id));
    }

    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return
     */
    @GetMapping("/details/{username}")
    public R<SysUser> user(@PathVariable String username) {
        SysUser condition = new SysUser();
        condition.setUsername(username);
        return R.ok(sysUserService.getOne(new QueryWrapper<>(condition)));
    }

    /**
     * 删除用户信息
     * @param id ID
     * @return R
     */
    @DeleteMapping("/{id:\\d+}")
    @PreAuthorize("@pms.hasPermission('sys_user_del')")
    public R<Boolean> userDel(@PathVariable Long id) {
        SysUser sysUser = sysUserService.getById(id);
        return R.ok(sysUserService.removeUserById(sysUser));
    }

    /**
     * 添加用户
     * @param userDto 用户信息
     * @return success/false
     */
    @PostMapping
    @PreAuthorize("@pms.hasPermission('sys_user_add')")
    public R<Boolean> user(@RequestBody UserDTO userDto) {
        return R.ok(sysUserService.saveUser(userDto));
    }

    /**
     * 更新用户信息
     * @param userDto 用户信息
     * @return R
     */
    @PutMapping
    @PreAuthorize("@pms.hasPermission('sys_user_edit')")
    public R<Boolean> updateUser(@Valid @RequestBody UserDTO userDto) {
        return R.ok(sysUserService.updateUser(userDto));
    }

    /**
     * 分页查询用户
     * @param page 参数集
     * @param userDTO 查询参数列表
     * @return 用户集合
     */
    @GetMapping("/page")
    public R<IPage<List<UserVO>>> getUserPage(Page page, UserDTO userDTO) {
        return R.ok(sysUserService.getUserWithRolePage(page, userDTO));
    }

    /**
     * 修改个人信息
     * @param userDto userDto
     * @return success/false
     */
    @PutMapping("/edit")
    public R<Boolean> updateUserInfo(@Valid @RequestBody UserDTO userDto) {
        return R.ok(sysUserService.updateUserInfo(userDto));
    }

    /**
     * @param username 用户名称
     * @return 上级部门用户列表
     */
    @GetMapping("/ancestor/{username}")
    public R<List<SysUser>> listAncestorUsers(@PathVariable String username) {
        return R.ok(sysUserService.listAncestorUsersByUsername(username));
    }

    /**
     * 导出excel 表格
     * @param userDTO 查询条件
     * @return
     */
    @ResponseExcel
    @GetMapping("/export")
    @PreAuthorize("@pms.hasPermission('sys_user_import_export')")
    public List<UserExcelVO> export(UserDTO userDTO) {
        return sysUserService.listUser(userDTO);
    }

    /**
     * 导入用户
     * @param excelVOList 用户列表
     * @param bindingResult 错误信息列表
     * @return R
     */
    @PostMapping("/import")
    @PreAuthorize("@pms.hasPermission('sys_user_import_export')")
    public R importUser(@RequestExcel List<UserExcelVO> excelVOList, BindingResult bindingResult) {
        return sysUserService.importUser(excelVOList, bindingResult);
    }

}
