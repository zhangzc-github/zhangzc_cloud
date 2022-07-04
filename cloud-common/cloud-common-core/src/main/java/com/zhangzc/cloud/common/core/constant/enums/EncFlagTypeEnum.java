package com.zhangzc.cloud.common.core.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EncFlagTypeEnum {

	/**
	 * 是
	 */
	YES("1", "是"),

	/**
	 * 否
	 */
	NO("0", "否");

	/**
	 * 类型
	 */
	private String type;

	/**
	 * 描述
	 */
	private String description;

}
