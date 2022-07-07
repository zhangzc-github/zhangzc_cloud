package com.zhangzc.cloud.auth.handler;

import com.zhangzc.cloud.common.core.util.SpringContextHolder;
import com.zhangzc.cloud.common.log.event.SysLogEvent;
import com.zhangzc.cloud.common.log.util.SysLogUtils;
import com.zhangzc.cloud.common.security.handler.AbstractAuthenticationSuccessEventHandler;
import com.zhangzc.cloud.upms.api.entity.SysLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CloudAuthenticationSuccessEventHandler extends AbstractAuthenticationSuccessEventHandler {

	/**
	 * 处理登录成功方法
	 * <p>
	 * 获取到登录的authentication 对象
	 * @param authentication 登录对象
	 */
	@Override
	public void handle(Authentication authentication) {
		log.info("用户：{} 登录成功", authentication.getPrincipal());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		SysLog logVo = SysLogUtils.getSysLog();
		logVo.setTitle("登录成功");
		// 发送异步日志事件
		Long startTime = System.currentTimeMillis();
		Long endTime = System.currentTimeMillis();
		logVo.setTime(endTime - startTime);
		logVo.setCreateBy(authentication.getName());
		SpringContextHolder.publishEvent(new SysLogEvent(logVo));
	}

}
