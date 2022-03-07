package com.zhangzc.cloud.common.log.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * TODO
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/3/7 12:23 下午
 */
@Getter
@RequiredArgsConstructor
public enum LogTypeEnum {

	/**
	 * 正常日志类型
	 */
	NORMAL("0", "正常日志"),

	/**
	 * 错误日志类型
	 */
	ERROR("9", "错误日志");

	/**
	 * 类型
	 */
	private final String type;

	/**
	 * 描述
	 */
	private final String description;

}
