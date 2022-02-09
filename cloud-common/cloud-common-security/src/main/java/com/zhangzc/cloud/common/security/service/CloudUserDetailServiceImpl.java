package com.zhangzc.cloud.common.security.service;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import java.util.HashSet;
import java.util.Set;

public class CloudUserDetailServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if ("admin".equals(s)){
            Set<String> dbAuthsSet = new HashSet<>();
            dbAuthsSet.add("ROLE_1");

            return new CloudUser(1L, 1L, "admin", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("123456"),"123",
                    true, true, true, true, AuthorityUtils.createAuthorityList(dbAuthsSet.toArray(new String[0])));
        } else {
            throw new UsernameNotFoundException("用户不存在");
        }
    }
}
