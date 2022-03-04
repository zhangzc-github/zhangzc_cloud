package com.zhangzc.cloud.common.security.component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.zhangzc.cloud.common.core.constant.CommonConstants;
import com.zhangzc.cloud.common.security.exception.CloudAuth2Exception;
import lombok.SneakyThrows;

/**
 * TODO
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/3/4 11:59 上午
 */
public class CloudAuth2ExceptionSerializer extends StdSerializer<CloudAuth2Exception> {

	public CloudAuth2ExceptionSerializer() {
		super(CloudAuth2Exception.class);
	}

	@Override
	@SneakyThrows
	public void serialize(CloudAuth2Exception value, JsonGenerator gen, SerializerProvider provider) {
		gen.writeStartObject();
		gen.writeObjectField("code", CommonConstants.FAIL);
		gen.writeStringField("msg", value.getMessage());
		gen.writeStringField("data", value.getErrorCode());
		// 资源服务器会读取这个字段
		gen.writeStringField("error", value.getMessage());
		gen.writeEndObject();
	}

}
