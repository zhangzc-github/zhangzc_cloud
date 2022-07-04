package com.zhangzc.cloud.upms.api.dto;

import com.zhangzc.cloud.upms.api.entity.SysOauthClientDetails;
import lombok.Data;

@Data
public class SysOauthClientDetailsDTO extends SysOauthClientDetails {

	/**
	 * 验证码开关
	 */
	private String captchaFlag;

	/**
	 * 前端密码传输是否加密
	 */
	private String encFlag;

}
