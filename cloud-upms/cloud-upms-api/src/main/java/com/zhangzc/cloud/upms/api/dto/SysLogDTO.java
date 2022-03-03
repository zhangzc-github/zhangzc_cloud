package com.zhangzc.cloud.upms.api.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * TODO
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/3/3 11:43 上午
 */
@Data
public class SysLogDTO {

	/**
	 * 查询日志类型
	 */
	private String type;

	/**
	 * 创建时间区间 [开始时间，结束时间]
	 */
	private LocalDateTime[] createTime;

}
