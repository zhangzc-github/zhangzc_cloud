package com.zhangzc.cloud.common.gray.feign;

import cn.hutool.core.util.StrUtil;
import com.zhangzc.cloud.common.core.constant.CommonConstants;
import com.zhangzc.cloud.common.core.util.WebUtils;
import com.zhangzc.cloud.common.gray.support.NonWebVersionContextHolder;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

/**
 * feign 请求VERSION 传递
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/7/8 5:47 下午
 */
@Slf4j
public class GrayFeignRequestInterceptor implements RequestInterceptor {

	/**
	 * Called for every request. Add data using methods on the supplied
	 * {@link RequestTemplate}.
	 * @param template
	 */
	@Override
	public void apply(RequestTemplate template) {
		String reqVersion = WebUtils.getRequest().isPresent() ? WebUtils.getRequest().get().getHeader(CommonConstants.VERSION)
				: NonWebVersionContextHolder.getVersion();

		if (StrUtil.isNotBlank(reqVersion)) {
			log.debug("feign gray add header version :{}", reqVersion);
			template.header(CommonConstants.VERSION, reqVersion);
		}
	}

}
