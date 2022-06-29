package com.zhangzc.cloud.common.security.handler;

import cn.hutool.core.util.StrUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 退出登录处理器
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/6/28 12:16 下午
 */
public class SsoLogoutSuccessHandler implements LogoutSuccessHandler {
    private static final String REDIRECT_URL = "redirect_url";
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        if (response == null) {
            return;
        }

        // 获取请求参数中是否包含 回调地址
        String redirectUrl = request.getParameter(REDIRECT_URL);
        if (StrUtil.isNotBlank(redirectUrl)) {
            response.sendRedirect(redirectUrl);
        }
        else {
            // 默认跳转referer 地址
            String referer = request.getHeader(HttpHeaders.REFERER);
            response.sendRedirect(referer);
        }
    }
}
