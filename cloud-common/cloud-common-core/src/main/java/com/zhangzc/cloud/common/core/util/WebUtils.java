package com.zhangzc.cloud.common.core.util;

import cn.hutool.core.codec.Base64;
import com.zhangzc.cloud.common.core.exception.CheckedException;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * TODO
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/2/22 11:44 上午
 */
@Slf4j
@UtilityClass
public class WebUtils extends org.springframework.web.util.WebUtils {

    private final String BASIC_ = "Basic ";

    /**
     * 从request 获取CLIENT_ID
     * @return
     */
    @SneakyThrows
    public String getClientId(ServerHttpRequest request) {
        String header = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        return splitClient(header)[0];
    }

    @NotNull
    private String[] splitClient(String header) {
        if (header == null || !header.startsWith(BASIC_)) {
            throw new CheckedException("请求头中client信息为空");
        }
        byte[] base64Token = header.substring(6).getBytes(StandardCharsets.UTF_8);
        byte[] decoded;
        try {
            decoded = Base64.decode(base64Token);
        }
        catch (IllegalArgumentException e) {
            throw new CheckedException("Failed to decode basic authentication token");
        }

        String token = new String(decoded, StandardCharsets.UTF_8);

        int delim = token.indexOf(":");

        if (delim == -1) {
            throw new CheckedException("Invalid basic authentication token");
        }
        return new String[] { token.substring(0, delim), token.substring(delim + 1) };
    }

    /**
     * 获取 HttpServletRequest
     * @return {HttpServletRequest}
     */
    public Optional<HttpServletRequest> getRequest() {
        return Optional
                .ofNullable(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest());
    }

    /**
     * 获取 HttpServletResponse
     * @return {HttpServletResponse}
     */
    public HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    /**
     * 解析 client id
     * @param header
     * @param defVal
     * @return 如果解析失败返回默认值
     */
    public String extractClientId(String header, final String defVal) {

        if (header == null || !header.startsWith(BASIC_)) {
            log.debug("请求头中client信息为空: {}", header);
            return defVal;
        }
        byte[] base64Token = header.substring(6).getBytes(StandardCharsets.UTF_8);
        byte[] decoded;
        try {
            decoded = Base64.decode(base64Token);
        }
        catch (IllegalArgumentException e) {
            log.debug("Failed to decode basic authentication token: {}", header);
            return defVal;
        }

        String token = new String(decoded, StandardCharsets.UTF_8);

        int delim = token.indexOf(":");

        if (delim == -1) {
            log.debug("Invalid basic authentication token: {}", header);
            return defVal;
        }
        return token.substring(0, delim);
    }

    /**
     * 从请求头中解析 client id
     * @param header
     * @return
     */
    public Optional<String> extractClientId(String header) {
        return Optional.ofNullable(extractClientId(header, null));
    }
}
