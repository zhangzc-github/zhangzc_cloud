package com.zhangzc.cloud.common.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhangzc.cloud.common.security.component.CloudAuth2ExceptionSerializer;

/**
 * TODO
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/3/4 12:06 下午
 */
@JsonSerialize(using = CloudAuth2ExceptionSerializer.class)
public class InvalidException extends CloudAuth2Exception {

	public InvalidException(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "invalid_exception";
	}

	@Override
	public int getHttpErrorCode() {
		return 426;
	}

}
