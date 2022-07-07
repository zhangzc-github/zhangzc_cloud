package com.zhangzc.cloud.common.security.handler;

import cn.hutool.core.collection.CollUtil;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.LogoutSuccessEvent;
import org.springframework.security.core.Authentication;

/**
 * 退出成功事件处理器
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/7/7 5:41 下午
 */
public abstract class AbstractLogoutSuccessEventHandler implements ApplicationListener<LogoutSuccessEvent> {

	/**
	 * Handle an application event.
	 * @param event the event to respond to
	 */
	@Override
	public void onApplicationEvent(LogoutSuccessEvent event) {
		Authentication authentication = (Authentication) event.getSource();
		if (CollUtil.isNotEmpty(authentication.getAuthorities())) {
			handle(authentication);
		}
	}

	/**
	 * 处理退出成功方法
	 * <p>
	 * 获取到登录的authentication 对象
	 * @param authentication 登录对象
	 */
	public abstract void handle(Authentication authentication);

}
