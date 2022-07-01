package com.zhangzc.cloud.upms.api.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 公共参数配置
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/7/1 12:29 下午
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysPublicParam extends Model<SysPublicParam> {

	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	@TableId
	private Long publicId;

	/**
	 * 公共参数名称
	 */
	private String publicName;

	/**
	 * 公共参数地址值,英文大写+下划线
	 */
	private String publicKey;

	/**
	 * 值
	 */
	private String publicValue;

	/**
	 * 状态（1有效；2无效；）
	 */
	private String status;

	/**
	 * 删除状态（0：正常 1：已删除）
	 */
	@TableLogic
	private String delFlag;

	/**
	 * 公共参数编码
	 */
	private String validateCode;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 修改时间
	 */
	private Date updateTime;

	/**
	 * 是否是系统内置
	 */
	@TableField(value = "`system`")
	private String system;

	/**
	 * 配置类型：0-默认；1-检索；2-原文；3-报表；4-安全；5-文档；6-消息；9-其他
	 */
	private String publicType;

}
