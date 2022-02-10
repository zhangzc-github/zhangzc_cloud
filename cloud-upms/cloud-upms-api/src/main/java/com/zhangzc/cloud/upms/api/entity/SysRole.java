package com.zhangzc.cloud.upms.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import javax.validation.constraints.NotBlank;

/**
 * 角色表
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/2/10 3:45 下午
 */
@Data
public class SysRole {

	private static final long serialVersionUID = 1L;

	@TableId(value = "role_id", type = IdType.ASSIGN_ID)
	private Long roleId;

	@NotBlank(message = "角色名称 不能为空")
	private String roleName;

	@NotBlank(message = "角色标识 不能为空")
	private String roleCode;

	@NotBlank(message = "角色描述 不能为空")
	private String roleDesc;

	/**
	 * 删除标识（0-正常,1-删除）
	 */
	@TableLogic
	private String delFlag;

}
