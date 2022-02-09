package com.zhangzc.cloud.common.security.service;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * 扩展用户信息
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/2/9 6:11 下午
 */
public class CloudUser extends User {
    /**
     * 用户ID
     */
    @Getter
    private Long id;
    /**
     * 部门ID
     */
    @Getter
    private Long deptId;
    /**
     * 手机号
     */
    @Getter
    private String phone;
    public CloudUser(Long id, Long deptId, String username, String password, String phone, boolean enabled,
                   boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked,
                   Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = id;
        this.deptId = deptId;
        this.phone = phone;
    }
}
