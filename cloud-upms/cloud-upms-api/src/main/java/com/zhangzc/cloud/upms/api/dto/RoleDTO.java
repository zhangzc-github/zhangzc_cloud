package com.zhangzc.cloud.upms.api.dto;

import com.zhangzc.cloud.upms.api.entity.SysRole;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * TODO
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/3/3 11:43 上午
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleDTO extends SysRole {

	/**
	 * 角色部门Id
	 */
	private Long roleDeptId;

	/**
	 * 部门名称
	 */
	private String deptName;

}
