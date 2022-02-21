package com.zhangzc.cloud.upms.api.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 部门关系表
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/2/21 11:00 上午
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysDeptRelation extends Model<SysDeptRelation> {

	private static final long serialVersionUID = 1L;

	/**
	 * 祖先节点
	 */
	private Long ancestor;

	/**
	 * 后代节点
	 */
	private Long descendant;

}
