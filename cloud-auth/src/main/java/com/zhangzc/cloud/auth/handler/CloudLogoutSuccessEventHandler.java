package com.zhangzc.cloud.auth.handler;

import com.zhangzc.cloud.common.core.util.SpringContextHolder;
import com.zhangzc.cloud.common.core.util.WebUtils;
import com.zhangzc.cloud.common.log.event.SysLogEvent;
import com.zhangzc.cloud.common.log.util.SysLogUtils;
import com.zhangzc.cloud.common.security.handler.AbstractLogoutSuccessEventHandler;
import com.zhangzc.cloud.upms.api.entity.SysLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CloudLogoutSuccessEventHandler extends AbstractLogoutSuccessEventHandler {

	/**
	 * 处理退出成功方法
	 * <p>
	 * 获取到登录的authentication 对象
	 * @param authentication 登录对象
	 */
	@Override
	public void handle(Authentication authentication) {
		log.info("用户：{} 退出成功", authentication.getPrincipal());
		SecurityContextHolder.getContext().setAuthentication(authentication);

		SysLog logVo = SysLogUtils.getSysLog();
		logVo.setTitle("退出成功");
		// 发送异步日志事件
		Long startTime = System.currentTimeMillis();
		Long endTime = System.currentTimeMillis();
		logVo.setTime(endTime - startTime);

		// 设置对应的token
		WebUtils.getRequest().ifPresent(request -> logVo.setParams(request.getHeader(HttpHeaders.AUTHORIZATION)));

		// 这边设置ServiceId
		if (authentication instanceof OAuth2Authentication) {
			OAuth2Authentication auth2Authentication = (OAuth2Authentication) authentication;
			logVo.setServiceId(auth2Authentication.getOAuth2Request().getClientId());
		}
		logVo.setCreateBy(authentication.getName());
		SpringContextHolder.publishEvent(new SysLogEvent(logVo));
	}

}
