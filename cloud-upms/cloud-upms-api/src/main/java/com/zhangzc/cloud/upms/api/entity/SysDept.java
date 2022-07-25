package com.zhangzc.cloud.upms.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 部门管理
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/7/1 3:48 下午
 */
@Schema(description = "部门")
@Data
@EqualsAndHashCode(callSuper = true)
public class SysDept extends Model<SysDept> {

	private static final long serialVersionUID = 1L;

	@Schema(description = "部门id")
	@TableId(value = "dept_id", type = IdType.AUTO)
	private Long deptId;

	/**
	 * 部门名称
	 */
	@Schema(description = "部门名称",required = true)
	@NotBlank(message = "部门名称不能为空")
	private String name;

	/**
	 * 排序
	 */
	@Schema(description = "排序值",required = true)
	@NotNull(message = "排序值不能为空")
	private Integer sortOrder;

	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;

	/**
	 * 修改时间
	 */
	private LocalDateTime updateTime;

	/**
	 * 父级部门id
	 */
	private Long parentId;

	/**
	 * 是否删除 1：已删除 0：正常
	 */
	@TableLogic
	private String delFlag;

}
