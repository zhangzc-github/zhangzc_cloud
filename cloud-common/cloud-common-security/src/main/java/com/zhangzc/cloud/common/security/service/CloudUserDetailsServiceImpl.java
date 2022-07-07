package com.zhangzc.cloud.common.security.service;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.zhangzc.cloud.common.core.constant.CacheConstants;
import com.zhangzc.cloud.common.core.constant.CommonConstants;
import com.zhangzc.cloud.common.core.constant.SecurityConstants;
import com.zhangzc.cloud.common.core.util.R;
import com.zhangzc.cloud.upms.api.dto.UserInfo;
import com.zhangzc.cloud.upms.api.entity.SysUser;
import com.zhangzc.cloud.upms.api.feign.RemoteUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.*;

/**
 * 查询用户信息
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/2/14 12:43 下午
 */
@RequiredArgsConstructor
@Primary
public class CloudUserDetailsServiceImpl implements CloudUserDetailsService {
    private final RemoteUserService remoteUserService;
    private final CacheManager cacheManager;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Cache cache = cacheManager.getCache(CacheConstants.USER_DETAILS);
        if (cache != null && cache.get(username) != null) {
            return (CloudUser) cache.get(username).get();
        }
        R<UserInfo> result = remoteUserService.userInfo(username, SecurityConstants.FROM_IN);
        UserDetails userDetails = getUserDetails(result);
        if (cache != null) {
            cache.put(username, userDetails);
        }
        return userDetails;
    }

    @Override
    public int getOrder() {
        return Integer.MIN_VALUE;
    }
}
