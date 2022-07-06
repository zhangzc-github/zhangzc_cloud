package com.zhangzc.cloud.gateway.filter;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.anji.captcha.model.vo.CaptchaVO;
import com.anji.captcha.service.CaptchaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhangzc.cloud.common.core.constant.CacheConstants;
import com.zhangzc.cloud.common.core.constant.CommonConstants;
import com.zhangzc.cloud.common.core.constant.SecurityConstants;
import com.zhangzc.cloud.common.core.constant.enums.CaptchaFlagTypeEnum;
import com.zhangzc.cloud.common.core.exception.ValidateCodeException;
import com.zhangzc.cloud.common.core.util.R;
import com.zhangzc.cloud.common.core.util.SpringContextHolder;
import com.zhangzc.cloud.common.core.util.WebUtils;
import com.zhangzc.cloud.gateway.config.GatewayConfigProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

/**
 * 验证码验证 filter
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/2/22 12:25 下午
 */
@Slf4j
@RequiredArgsConstructor
public class ValidateCodeGatewayFilter extends AbstractGatewayFilterFactory<Object> {
    private final ObjectMapper objectMapper;
    private final RedisTemplate<String, Object> redisTemplate;
    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            boolean isAuthToken = CharSequenceUtil.containsAnyIgnoreCase(request.getURI().getPath(),
                    SecurityConstants.OAUTH_TOKEN_URL,
                    SecurityConstants.SMS_TOKEN_URL);

            //不是登陆请求，直接向下执行
            if (!isAuthToken) {
                return chain.filter(exchange);
            }

            //刷新token
            String grantType = request.getQueryParams().getFirst("grant_type");
            if (StrUtil.equals(SecurityConstants.REFRESH_TOKEN, grantType)) {
                return chain.filter(exchange);
            }

            // 判断客户端是否跳过检验
            if (!isCheckCaptchaClient(request)) {
                return chain.filter(exchange);
            }

            try {
                checkCode(request);
            } catch (Exception e) {
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.PRECONDITION_REQUIRED);
                response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

                String errMsg = e.getMessage();

                return response.writeWith(Mono.create(monoSink -> {
                    try {
                        byte[] bytes = objectMapper.writeValueAsBytes(R.failed(errMsg));
                        DataBuffer dataBuffer = response.bufferFactory().wrap(bytes);

                        monoSink.success(dataBuffer);
                    } catch (JsonProcessingException jsonProcessingException) {
                        log.error("对象输出异常", jsonProcessingException);
                        monoSink.error(jsonProcessingException);
                    }
                }));
            }


            return chain.filter(exchange);
        };
    }

    /**
     * 是否需要校验客户端，根据client 查询客户端配置
     * @param request 请求
     * @return true 需要校验， false 不需要校验
     */
    private boolean isCheckCaptchaClient(ServerHttpRequest request) {
        String header = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        String clientId = WebUtils.extractClientId(header).orElse(null);
        // 获取租户拼接区分租户的key
        String tenantId = request.getHeaders().getFirst(CommonConstants.TENANT_ID);
        String key = String.format("%s:%s:%s", StrUtil.isBlank(tenantId) ? CommonConstants.TENANT_ID_1 : tenantId,
                CacheConstants.CLIENT_FLAG, clientId);

        redisTemplate.setKeySerializer(RedisSerializer.string());
        Object val = redisTemplate.opsForValue().get(key);

        // 当配置不存在时，默认需要校验
        if (val == null) {
            return true;
        }

        JSONObject information = JSONUtil.parseObj(val.toString());
        if (StrUtil.equals(CaptchaFlagTypeEnum.OFF.getType(), information.getStr(CommonConstants.CAPTCHA_FLAG))) {
            return false;
        }
        return true;
    }

    private void checkCode(ServerHttpRequest request) {
        String code = request.getQueryParams().getFirst("code");

        if (CharSequenceUtil.isBlank(code)) {
            throw new ValidateCodeException("验证码不能为空");
        }

        String randomStr = request.getQueryParams().getFirst("randomStr");

        // 若是滑块登录
        if (CommonConstants.IMAGE_CODE_TYPE.equalsIgnoreCase(randomStr)) {
            CaptchaService captchaService = SpringContextHolder.getBean(CaptchaService.class);
            CaptchaVO vo = new CaptchaVO();
            vo.setCaptchaVerification(code);
            vo.setCaptchaType(CommonConstants.IMAGE_CODE_TYPE);
            if (!captchaService.verification(vo).isSuccess()) {
                throw new ValidateCodeException("验证码不能为空");
            }
            return;
        }

        String mobile = request.getQueryParams().getFirst("mobile");
        if (StrUtil.isNotBlank(mobile)) {
            randomStr = mobile;
        }

        String key = CacheConstants.DEFAULT_CODE_KEY + randomStr;
        redisTemplate.setKeySerializer(RedisSerializer.string());

        if (!redisTemplate.hasKey(key)) {
            throw new ValidateCodeException("验证码不合法");
        }

        Object codeObj = redisTemplate.opsForValue().get(key);

        if (codeObj == null) {
            throw new ValidateCodeException("验证码不合法");
        }

        String saveCode = codeObj.toString();
        if (StrUtil.isBlank(saveCode)) {
            redisTemplate.delete(key);
            throw new ValidateCodeException("验证码不合法");
        }

        if (!StrUtil.equals(saveCode, code)) {
            redisTemplate.delete(key);
            throw new ValidateCodeException("验证码不合法");
        }

        redisTemplate.delete(key);
    }
}
