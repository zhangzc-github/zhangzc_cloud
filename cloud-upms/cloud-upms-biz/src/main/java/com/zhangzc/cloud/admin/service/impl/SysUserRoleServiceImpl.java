package com.zhangzc.cloud.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangzc.cloud.admin.mapper.SysUserRoleMapper;
import com.zhangzc.cloud.admin.service.SysUserRoleService;
import com.zhangzc.cloud.upms.api.entity.SysUserRole;
import org.springframework.stereotype.Service;

@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {
}
