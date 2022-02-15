package com.zhangzc.cloud.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangzc.cloud.upms.api.dto.UserInfo;
import com.zhangzc.cloud.upms.api.entity.SysUser;

/**
 * SysUser Service接口
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/2/14 11:15 上午
 */
public interface SysUserService extends IService<SysUser> {
    UserInfo getUserInfo(SysUser sysUser);
}
