package com.zhangzc.cloud.upms.api.dto;

import com.zhangzc.cloud.upms.api.entity.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * user dto
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/2/21 11:27 上午
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends SysUser {

	/**
	 * 角色ID
	 */
	private List<Long> role;

	/**
	 * 部门ID
	 */
	private Long deptId;

	/**
	 * 新密码
	 */
	private String newpassword1;

}
