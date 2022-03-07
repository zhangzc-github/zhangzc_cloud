package com.zhangzc.cloud.common.log.event;

import com.zhangzc.cloud.common.core.constant.SecurityConstants;
import com.zhangzc.cloud.upms.api.entity.SysLog;
import com.zhangzc.cloud.upms.api.feign.RemoteLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

@RequiredArgsConstructor
public class SysLogListener {

    private final RemoteLogService remoteLogService;

    @Async
    @Order
    @EventListener(SysLogEvent.class)
    public void sysSaveLog(SysLogEvent event) {
        SysLog sysLog = (SysLog) event.getSource();
        remoteLogService.save(sysLog, SecurityConstants.FROM_IN);
    }
}
