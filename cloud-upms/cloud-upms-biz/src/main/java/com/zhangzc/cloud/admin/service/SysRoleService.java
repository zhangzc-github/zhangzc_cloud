package com.zhangzc.cloud.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangzc.cloud.upms.api.entity.SysRole;

public interface SysRoleService extends IService<SysRole> {
    /**
     * 通过角色ID，删除角色
     * @param id
     * @return
     */
    Boolean removeRoleById(Long id);
}
