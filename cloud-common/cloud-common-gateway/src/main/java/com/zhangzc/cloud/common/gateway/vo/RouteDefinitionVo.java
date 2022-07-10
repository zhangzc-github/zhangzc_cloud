package com.zhangzc.cloud.common.gateway.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.cloud.gateway.route.RouteDefinition;

import java.io.Serializable;

/**
 * 扩展此类支持序列化 See RouteDefinition.class
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/7/8 3:26 下午
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RouteDefinitionVo extends RouteDefinition implements Serializable {

	/**
	 * 路由名称
	 */
	private String routeName;

}
