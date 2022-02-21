package com.zhangzc.cloud.common.security.util;

import com.zhangzc.cloud.common.security.service.CloudUser;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@UtilityClass
public class SecurityUtils {
    /**
     * 获取 Authentication
     * @return Authentication
     */
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取 CloudUser
     * @param authentication Authentication
     * @return CloudUser
     */
    public CloudUser getUser(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof CloudUser) {
            return (CloudUser) principal;
        }
        return null;
    }

    /**
     * 获取 CloudUser
     * @return CloudUser
     */
    public CloudUser getUser(){
        Authentication authentication = getAuthentication();
        if (authentication == null) {
            return null;
        }
        return getUser(authentication);
    }
}
