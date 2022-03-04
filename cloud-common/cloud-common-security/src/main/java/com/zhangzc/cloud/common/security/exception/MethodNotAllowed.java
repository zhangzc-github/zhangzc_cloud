package com.zhangzc.cloud.common.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhangzc.cloud.common.security.component.CloudAuth2ExceptionSerializer;
import org.springframework.http.HttpStatus;

/**
 * TODO
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/3/4 12:02 下午
 */
@JsonSerialize(using = CloudAuth2ExceptionSerializer.class)
public class MethodNotAllowed extends CloudAuth2Exception {

	public MethodNotAllowed(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "method_not_allowed";
	}

	@Override
	public int getHttpErrorCode() {
		return HttpStatus.METHOD_NOT_ALLOWED.value();
	}

}
