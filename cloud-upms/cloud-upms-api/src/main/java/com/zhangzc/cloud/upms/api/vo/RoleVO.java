package com.zhangzc.cloud.upms.api.vo;

import lombok.Data;

/**
 * TODO
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/2/22 10:15 上午
 */
@Data
public class RoleVO {

	/**
	 * 角色id
	 */
	private Long roleId;

	/**
	 * 菜单列表
	 */
	private String menuIds;

}
