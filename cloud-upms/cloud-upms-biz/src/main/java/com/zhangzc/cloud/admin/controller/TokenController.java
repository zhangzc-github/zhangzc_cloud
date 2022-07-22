package com.zhangzc.cloud.admin.controller;

import com.zhangzc.cloud.common.core.constant.SecurityConstants;
import com.zhangzc.cloud.common.core.util.R;
import com.zhangzc.cloud.upms.api.feign.RemoteTokenService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
@Tag(name = "令牌管理")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class TokenController {

    private final RemoteTokenService remoteTokenService;

    /**
     * 分页token 信息
     * @param params 参数集
     * @return token集合
     */
    @GetMapping("/page")
    public R token(@RequestParam Map<String, Object> params) {
        return remoteTokenService.getTokenPage(params, SecurityConstants.FROM_IN);
    }

    /**
     * 删除
     * @param id ID
     * @return success/false
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('sys_token_del')")
    public R<Boolean> delete(@PathVariable String id) {
        return remoteTokenService.removeToken(id, SecurityConstants.FROM_IN);
    }
}
