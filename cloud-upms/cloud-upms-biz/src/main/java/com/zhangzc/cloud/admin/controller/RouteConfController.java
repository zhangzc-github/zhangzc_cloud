package com.zhangzc.cloud.admin.controller;

import cn.hutool.json.JSONArray;
import com.zhangzc.cloud.admin.service.SysRouteConfService;
import com.zhangzc.cloud.common.core.util.R;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/route")
@Tag(name = "动态路由配置")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class RouteConfController {

	private final SysRouteConfService sysRouteConfService;

	/**
	 * 获取当前定义的路由信息
	 * @return
	 */
	@GetMapping
	public R listRoutes() {
		return R.ok(sysRouteConfService.list());
	}

	/**
	 * 修改路由
	 * @param routes 路由定义
	 * @return
	 */
	@PutMapping
	public R updateRoutes(@RequestBody JSONArray routes) {
		return R.ok(sysRouteConfService.updateRoutes(routes));
	}

}
