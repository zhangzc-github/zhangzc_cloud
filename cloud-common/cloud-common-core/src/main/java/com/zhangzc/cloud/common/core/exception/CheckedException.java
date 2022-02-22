package com.zhangzc.cloud.common.core.exception;

import lombok.NoArgsConstructor;

/**
 * TODO
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/2/22 11:44 上午
 */
@NoArgsConstructor
public class CheckedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CheckedException(String message) {
        super(message);
    }

    public CheckedException(Throwable cause) {
        super(cause);
    }

    public CheckedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CheckedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
