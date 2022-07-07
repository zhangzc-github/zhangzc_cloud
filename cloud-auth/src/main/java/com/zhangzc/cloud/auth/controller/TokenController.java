package com.zhangzc.cloud.auth.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhangzc.cloud.common.core.constant.CacheConstants;
import com.zhangzc.cloud.common.core.constant.CommonConstants;
import com.zhangzc.cloud.common.core.constant.SecurityConstants;
import com.zhangzc.cloud.common.core.util.R;
import com.zhangzc.cloud.common.core.util.SpringContextHolder;
import com.zhangzc.cloud.common.security.annotation.Inner;
import com.zhangzc.cloud.common.security.util.SecurityUtils;
import com.zhangzc.cloud.upms.api.entity.SysTenant;
import com.zhangzc.cloud.upms.api.feign.RemoteTenantService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.event.LogoutSuccessEvent;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * TokenController
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/3/1 5:33 下午
 */
@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class TokenController {

    private final TokenStore tokenStore;

    private final RedisTemplate redisTemplate;

    private final CacheManager cacheManager;

    private final RemoteTenantService tenantService;

    private final ClientDetailsService clientDetailsService;

    /**
     * 认证页面
     * @param modelAndView ModelAndView
     * @param error 表单登录失败处理回调的错误信息
     * @return ModelAndView
     */
    @GetMapping("/login")
    public ModelAndView login(ModelAndView modelAndView, @RequestParam(required = false) String error) {
        R<List<SysTenant>> tenantList = tenantService.list(SecurityConstants.FROM_IN);
        modelAndView.addObject("error", error);
        modelAndView.addObject("tenantList", tenantList.getData());
        modelAndView.setViewName("/ftl/login");
        return modelAndView;
    }

    /**
     * 退出并删除token
     * @param authHeader Authorization
     */
    @DeleteMapping("/logout")
    public R<Boolean> logout(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authHeader) {
        if (StrUtil.isBlank(authHeader)) {
            return R.ok();
        }

        String tokenValue = authHeader.replace(OAuth2AccessToken.BEARER_TYPE, StrUtil.EMPTY).trim();
        return removeToken(tokenValue);
    }

    /**
     * 确认授权页面
     * @param request
     * @param session
     * @param modelAndView
     * @return
     */
    @GetMapping("/confirm_access")
    public ModelAndView confirm(HttpServletRequest request, HttpSession session, ModelAndView modelAndView) {
        Map<String, Object> scopeList = (Map<String, Object>) request.getAttribute("scopes");
        modelAndView.addObject("scopeList", scopeList.keySet());

        Object auth = session.getAttribute("authorizationRequest");
        if (auth != null) {
            AuthorizationRequest authorizationRequest = (AuthorizationRequest) auth;
            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(authorizationRequest.getClientId());
            modelAndView.addObject("app", clientDetails.getAdditionalInformation());
            modelAndView.addObject("user", SecurityUtils.getUser());
        }

        modelAndView.setViewName("ftl/confirm");
        return modelAndView;
    }

    /**
     * 令牌管理调用
     * @param token token
     */
    @Inner
    @DeleteMapping("/{token}")
    public R<Boolean> removeToken(@PathVariable("token") String token) {
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(token);
        if (accessToken == null || StrUtil.isBlank(accessToken.getValue())) {
            return R.ok();
        }

        OAuth2Authentication auth2Authentication = tokenStore.readAuthentication(accessToken);
        // 清空用户信息
        cacheManager.getCache(CacheConstants.USER_DETAILS).evict(auth2Authentication.getName());

        // 清空access token
        tokenStore.removeAccessToken(accessToken);

        // 清空 refresh token
        OAuth2RefreshToken refreshToken = accessToken.getRefreshToken();
        tokenStore.removeRefreshToken(refreshToken);

        // 处理自定义退出事件，保存相关日志
        SpringContextHolder.publishEvent(new LogoutSuccessEvent(auth2Authentication));
        return R.ok();
    }

    /**
     * 查询token
     * @param params 分页参数
     * @return page
     */
    @Inner
    @PostMapping("/page")
    public R<Page> tokenList(@RequestBody Map<String, Object> params) {
        String key = "auth_to_access:*";
        int current = MapUtil.getInt(params, CommonConstants.CURRENT);
        int size = MapUtil.getInt(params, CommonConstants.SIZE);
        Set<String> keys = redisTemplate.keys(key);
        List<String> pages = keys.stream().skip((current - 1) * size).limit(size).collect(Collectors.toList());
        Page result = new Page<>(current, size);
        result.setRecords(redisTemplate.opsForValue().multiGet(pages));
        result.setTotal(keys.size());
        return R.ok(result);
    }
}
