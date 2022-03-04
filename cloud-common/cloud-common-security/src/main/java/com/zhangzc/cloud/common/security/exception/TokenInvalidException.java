package com.zhangzc.cloud.common.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhangzc.cloud.common.security.component.CloudAuth2ExceptionSerializer;
import org.springframework.http.HttpStatus;

/**
 * @author lengleng
 * @date 2021-08-05
 * <p>
 * 令牌不合法
 */
@JsonSerialize(using = CloudAuth2ExceptionSerializer.class)
public class TokenInvalidException extends CloudAuth2Exception {

	public TokenInvalidException(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "invalid_token";
	}

	@Override
	public int getHttpErrorCode() {
		return HttpStatus.FAILED_DEPENDENCY.value();
	}

}
