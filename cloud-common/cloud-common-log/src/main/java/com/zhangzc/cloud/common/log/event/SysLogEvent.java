package com.zhangzc.cloud.common.log.event;

import com.zhangzc.cloud.upms.api.entity.SysLog;
import org.springframework.context.ApplicationEvent;

/**
 * 日志事件
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/3/7 11:51 上午
 */
public class SysLogEvent extends ApplicationEvent {
    public SysLogEvent(SysLog sysLog) {
        super(sysLog);
    }
}
