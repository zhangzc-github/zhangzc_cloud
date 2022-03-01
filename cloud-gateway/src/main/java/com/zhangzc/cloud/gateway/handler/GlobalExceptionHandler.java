package com.zhangzc.cloud.gateway.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhangzc.cloud.common.core.util.R;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.handler.ResponseStatusExceptionHandler;
import reactor.core.publisher.Mono;

/**
 * 网关异常通用处理器，只作用在webflux 环境下 , 优先级低于 {@link ResponseStatusExceptionHandler} 执行
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/2/22 3:01 下午
 */
@Slf4j
@Order(-1)
@RequiredArgsConstructor
public class GlobalExceptionHandler implements ErrorWebExceptionHandler {

	private final ObjectMapper objectMapper;

	@Override
	public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
		ServerHttpResponse response = exchange.getResponse();

		if (response.isCommitted()) {
			return Mono.error(ex);
		}

		// header set
		response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
		if (ex instanceof ResponseStatusException) {
			response.setStatusCode(((ResponseStatusException) ex).getStatus());
		}

		return response.writeWith(Mono.fromSupplier(() -> {
			DataBufferFactory bufferFactory = response.bufferFactory();
			try {
				log.warn("Error Spring Cloud Gateway : {}", ex.getMessage());
				return bufferFactory.wrap(objectMapper.writeValueAsBytes(R.failed(ex.getMessage())));
			}
			catch (JsonProcessingException e) {
				log.error("Error writing response", ex);
				return bufferFactory.wrap(new byte[0]);
			}
		}));
	}

}
