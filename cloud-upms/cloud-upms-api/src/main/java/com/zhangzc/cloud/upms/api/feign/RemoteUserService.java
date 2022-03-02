package com.zhangzc.cloud.upms.api.feign;

import com.zhangzc.cloud.common.core.constant.SecurityConstants;
import com.zhangzc.cloud.common.core.util.R;
import com.zhangzc.cloud.upms.api.dto.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(contextId = "remoteUserService", value = "cloud-upms-biz")
public interface RemoteUserService {
    /**
     * 通过用户名查询用户信息、角色信息
     * @param username username
     * @return R<UserInfo>
     */
    @GetMapping("/user/info/{username}")
    R<UserInfo> userInfo(@PathVariable String username, @RequestHeader(SecurityConstants.FROM) String from);
}
