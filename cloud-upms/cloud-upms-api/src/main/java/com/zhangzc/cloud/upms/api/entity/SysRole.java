package com.zhangzc.cloud.upms.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 角色表
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/7/1 3:45 下午
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysRole extends Model<SysRole> {

	private static final long serialVersionUID = 1L;

	@TableId(value = "role_id", type = IdType.AUTO)
	private Long roleId;

	@NotBlank(message = "角色名称不能为空")
	private String roleName;

	@NotBlank(message = "角色标识不能为空")
	private String roleCode;

	private String roleDesc;

	@NotNull(message = "数据权限类型不能为空")
	private Integer dsType;

	/**
	 * 数据权限作用范围
	 */
	private String dsScope;

	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;

	/**
	 * 修改时间
	 */
	private LocalDateTime updateTime;

	/**
	 * 删除标识（0-正常,1-删除）
	 */
	@TableLogic
	private String delFlag;

}
