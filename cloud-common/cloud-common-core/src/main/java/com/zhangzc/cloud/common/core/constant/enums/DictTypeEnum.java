package com.zhangzc.cloud.common.core.constant.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * TODO
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/3/3 12:04 下午
 */
@Getter
@RequiredArgsConstructor
public enum DictTypeEnum {

	/**
	 * 字典类型-系统内置（不可修改）
	 */
	SYSTEM("1", "系统内置"),

	/**
	 * 字典类型-业务类型
	 */
	BIZ("0", "业务类");

	/**
	 * 类型
	 */
	private final String type;

	/**
	 * 描述
	 */
	private final String description;

}
