package com.zhangzc.cloud.upms.api.feign;

import com.zhangzc.cloud.common.core.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(contextId = "remoteTokenService", value = "cloud-auth")
public interface RemoteTokenService {

    /**
     * 分页查询token 信息
     * @param params 分页参数
     * @return page
     */
    @PostMapping("/token/page")
    R getTokenPage(@RequestBody Map<String, Object> params);
}
