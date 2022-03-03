package com.zhangzc.cloud.upms.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.zhangzc.cloud.common.mybatis.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * TODO
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/3/3 11:04 上午
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysDictItem extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	@TableId(type = IdType.ASSIGN_ID)
	private Long id;

	/**
	 * 所属字典类id
	 */
	private Long dictId;

	/**
	 * 数据值
	 */
	private String value;

	/**
	 * 标签名
	 */
	private String label;

	/**
	 * 类型
	 */
	private String type;

	/**
	 * 描述
	 */
	private String description;

	/**
	 * 排序（升序）
	 */
	private Integer sortOrder;

	/**
	 * 备注信息
	 */
	private String remark;

	/**
	 * 删除标记
	 */
	@TableLogic
	private String delFlag;

}
