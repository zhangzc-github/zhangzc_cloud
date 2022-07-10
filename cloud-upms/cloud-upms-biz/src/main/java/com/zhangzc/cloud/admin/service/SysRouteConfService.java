package com.zhangzc.cloud.admin.service;

import cn.hutool.json.JSONArray;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangzc.cloud.upms.api.entity.SysRouteConf;
import reactor.core.publisher.Mono;

public interface SysRouteConfService extends IService<SysRouteConf> {

	/**
	 * 更新路由信息
	 * @param routes 路由信息
	 * @return
	 */
	Mono<Void> updateRoutes(JSONArray routes);

}
