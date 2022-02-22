package com.zhangzc.cloud.common.core.exception;

/**
 * TODO
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/2/22 11:47 上午
 */
public class ValidateCodeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ValidateCodeException() {
	}

	public ValidateCodeException(String msg) {
		super(msg);
	}

}
