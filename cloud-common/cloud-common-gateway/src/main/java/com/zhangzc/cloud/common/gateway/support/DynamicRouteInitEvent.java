package com.zhangzc.cloud.common.gateway.support;

import org.springframework.context.ApplicationEvent;

public class DynamicRouteInitEvent extends ApplicationEvent {

	public DynamicRouteInitEvent(Object source) {
		super(source);
	}

}
