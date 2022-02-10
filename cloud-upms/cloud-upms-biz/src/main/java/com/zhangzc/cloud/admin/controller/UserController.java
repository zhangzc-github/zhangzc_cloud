package com.zhangzc.cloud.admin.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zhangzc.cloud.admin.service.SysUserService;
import com.zhangzc.cloud.common.core.utils.R;
import com.zhangzc.cloud.umps.api.dto.UserInfo;
import com.zhangzc.cloud.upms.api.entity.SysUser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/info/{username}")
    public R<UserInfo> userInfo(@PathVariable String username){
        SysUser sysUser = sysUserService.getOne(Wrappers.<SysUser>query().lambda().eq(SysUser::getUsername, username));
        if (sysUser == null) {
            return R.failed(String.format("用户信息为空 %s", username));
        }
        return R.ok(sysUserService.getUserInfo(sysUser));
    }
}
