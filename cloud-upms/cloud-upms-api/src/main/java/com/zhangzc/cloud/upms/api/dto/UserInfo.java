package com.zhangzc.cloud.upms.api.dto;

import com.zhangzc.cloud.upms.api.entity.SysRole;
import com.zhangzc.cloud.upms.api.entity.SysUser;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserInfo implements Serializable {

    /**
     * 用户基本信息
     */
    private SysUser sysUser;

    /**
     * 权限标识集合
     */
    private String[] permissions;

    /**
     * 角色集合
     */
    private Long[] roles;

    /**
     * 角色集合
     */
    private List<SysRole> roleList;

}
