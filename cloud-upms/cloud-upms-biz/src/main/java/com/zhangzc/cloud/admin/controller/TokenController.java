package com.zhangzc.cloud.admin.controller;

import com.zhangzc.cloud.common.core.util.R;
import com.zhangzc.cloud.upms.api.feign.RemoteTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class TokenController {

    private final RemoteTokenService remoteTokenService;

    @GetMapping("/page")
    public R token(@RequestParam Map<String, Object> params) {
        return remoteTokenService.getTokenPage(params);
    }
}
