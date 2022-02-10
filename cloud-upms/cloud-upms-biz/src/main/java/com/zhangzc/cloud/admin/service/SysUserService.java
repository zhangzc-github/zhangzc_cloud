package com.zhangzc.cloud.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangzc.cloud.umps.api.dto.UserInfo;
import com.zhangzc.cloud.upms.api.entity.SysUser;

public interface SysUserService extends IService<SysUser> {
    UserInfo getUserInfo(SysUser sysUser);
}
