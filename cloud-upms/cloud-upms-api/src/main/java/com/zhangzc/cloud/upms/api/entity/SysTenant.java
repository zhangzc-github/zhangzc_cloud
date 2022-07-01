package com.zhangzc.cloud.upms.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 租户
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/7/1 12:17 下午
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysTenant extends Model<SysTenant> {

	private static final long serialVersionUID = 1L;

	/**
	 * 租户id
	 */
	@TableId
	private Long id;

	/**
	 * 租户名称
	 */
	private String name;

	/**
	 * 租户编号
	 */
	private String code;

	/**
	 * 开始时间
	 */
	private LocalDateTime startTime;

	/**
	 * 结束时间
	 */
	private LocalDateTime endTime;

	/**
	 * 0正常 9-冻结
	 */
	private String status;

	/**
	 * 删除标记
	 */
	@TableLogic
	private String delFlag;

	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;

	/**
	 * 更新时间
	 */
	private LocalDateTime updateTime;

}
