package com.zhangzc.cloud.gateway.handler;

import com.anji.captcha.model.common.ResponseModel;
import com.anji.captcha.model.vo.CaptchaVO;
import com.anji.captcha.service.CaptchaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhangzc.cloud.common.core.constant.CommonConstants;
import com.zhangzc.cloud.common.core.util.R;
import com.zhangzc.cloud.common.core.util.SpringContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * 验证码处理类
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/7/1 11:12 上午
 */
@RequiredArgsConstructor
public class ImageCodeCheckHandler implements HandlerFunction<ServerResponse> {

    private final ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public Mono<ServerResponse> handle(ServerRequest request) {
        CaptchaVO vo = new CaptchaVO();
        vo.setPointJson(request.queryParam("pointJson").get());
        vo.setToken(request.queryParam("token").get());
        vo.setCaptchaType(CommonConstants.IMAGE_CODE_TYPE);

        CaptchaService captchaService = SpringContextHolder.getBean(CaptchaService.class);
        ResponseModel responseModel = captchaService.check(vo);

        return ServerResponse.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(objectMapper.writeValueAsString(R.ok(responseModel))));
    }
}
