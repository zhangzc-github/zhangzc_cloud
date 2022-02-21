package com.zhangzc.cloud.admin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangzc.cloud.admin.mapper.SysRoleMapper;
import com.zhangzc.cloud.admin.mapper.SysUserRoleMapper;
import com.zhangzc.cloud.admin.service.SysRoleService;
import com.zhangzc.cloud.upms.api.entity.SysRole;
import com.zhangzc.cloud.upms.api.entity.SysUserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    private final SysUserRoleMapper sysUserRoleMapper;

    /**
     * 通过角色ID，删除角色
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean removeRoleById(Long id) {
        sysUserRoleMapper.delete(Wrappers.<SysUserRole>update().lambda().eq(SysUserRole::getRoleId, id));
        return this.removeById(id);
    }
}
