package com.zhangzc.cloud.auth.handler;

import com.zhangzc.cloud.common.core.util.SpringContextHolder;
import com.zhangzc.cloud.common.log.event.SysLogEvent;
import com.zhangzc.cloud.common.log.util.LogTypeEnum;
import com.zhangzc.cloud.common.log.util.SysLogUtils;
import com.zhangzc.cloud.common.security.handler.AbstractAuthenticationFailureEventHandler;
import com.zhangzc.cloud.upms.api.entity.SysLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class CloudAuthenticationFailureEventHandler extends AbstractAuthenticationFailureEventHandler {

	/**
	 * 处理登录失败方法
	 * <p>
	 * @param authenticationException 登录的authentication 对象
	 * @param authentication 登录的authenticationException 对象
	 */
	@Override
	public void handle(AuthenticationException authenticationException, Authentication authentication) {
		log.info("用户：{} 登录失败，异常：{}", authentication.getPrincipal(), authenticationException.getLocalizedMessage());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		SysLog logVo = SysLogUtils.getSysLog();
		logVo.setTitle("登录失败");
		logVo.setType(LogTypeEnum.ERROR.getType());
		logVo.setException(authenticationException.getMessage());
		// 发送异步日志事件
		Long startTime = System.currentTimeMillis();
		Long endTime = System.currentTimeMillis();
		logVo.setTime(endTime - startTime);
		logVo.setCreateBy(authentication.getName());
		SpringContextHolder.publishEvent(new SysLogEvent(logVo));
	}

}
