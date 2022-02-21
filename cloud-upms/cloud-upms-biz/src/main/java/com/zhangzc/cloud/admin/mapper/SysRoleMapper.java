package com.zhangzc.cloud.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhangzc.cloud.upms.api.entity.SysRole;
import java.util.List;

public interface SysRoleMapper extends BaseMapper<SysRole> {
    /**
     * 通过user id查询用户角色信息
     * @param userId user id
     * @return 用户角色信息
     */
    List<SysRole> listRolesByUserId(Long userId);
}
