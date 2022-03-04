package com.zhangzc.cloud.common.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhangzc.cloud.common.security.component.CloudAuth2ExceptionSerializer;
import lombok.Getter;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * TODO
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/3/4 12:10 下午
 */
@JsonSerialize(using = CloudAuth2ExceptionSerializer.class)
public class CloudAuth2Exception extends OAuth2Exception {
    @Getter
    private String errorCode;

    public CloudAuth2Exception(String msg, String errorCode) {
        super(msg);
        this.errorCode = errorCode;
    }

    public CloudAuth2Exception(String msg) {
        super(msg);
    }
}
