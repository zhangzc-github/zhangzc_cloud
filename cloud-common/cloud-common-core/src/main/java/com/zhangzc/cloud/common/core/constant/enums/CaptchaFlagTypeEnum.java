package com.zhangzc.cloud.common.core.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CaptchaFlagTypeEnum {

	/**
	 * 开启验证码
	 */
	ON("1", "开启验证码"),

	/**
	 * 关闭验证码
	 */
	OFF("0", "关闭验证码");

	/**
	 * 类型
	 */
	private String type;

	/**
	 * 描述
	 */
	private String description;

}
