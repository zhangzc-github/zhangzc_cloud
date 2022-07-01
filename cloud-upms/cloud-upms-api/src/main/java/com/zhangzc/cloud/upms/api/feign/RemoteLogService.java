package com.zhangzc.cloud.upms.api.feign;

import com.zhangzc.cloud.common.core.constant.SecurityConstants;
import com.zhangzc.cloud.common.core.constant.ServiceNameConstants;
import com.zhangzc.cloud.common.core.util.R;
import com.zhangzc.cloud.upms.api.entity.SysLog;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import javax.validation.Valid;

/**
 * TODO
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/3/7 12:00 下午
 */
@FeignClient(contextId = "remoteLogService", value = ServiceNameConstants.UPMS_SERVICE)
public interface RemoteLogService {
    /**
     * 保存日志
     * @param sysLog SysLog
     * @param from 内部调用标志
     * @return 是否成功
     */
    @PostMapping("/log")
    R<Boolean> save(@Valid @RequestBody SysLog sysLog, @RequestHeader(SecurityConstants.FROM) String from);
}
