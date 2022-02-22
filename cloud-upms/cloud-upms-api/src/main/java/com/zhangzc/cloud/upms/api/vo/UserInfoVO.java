package com.zhangzc.cloud.upms.api.vo;

import com.zhangzc.cloud.upms.api.entity.SysUser;
import lombok.Data;
import java.io.Serializable;

/**
 * TODO
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/2/22 10:15 上午
 */
@Data
public class UserInfoVO implements Serializable {

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

}
