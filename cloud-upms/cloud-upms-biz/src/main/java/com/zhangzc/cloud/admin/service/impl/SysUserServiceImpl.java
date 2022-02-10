package com.zhangzc.cloud.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangzc.cloud.admin.mapper.SysUserMapper;
import com.zhangzc.cloud.admin.service.SysUserService;
import com.zhangzc.cloud.umps.api.dto.UserInfo;
import com.zhangzc.cloud.upms.api.entity.SysUser;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Override
    public UserInfo getUserInfo(SysUser sysUser) {
        return null;
    }
}
