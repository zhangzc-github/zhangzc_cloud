package com.zhangzc.cloud.admin.controller;

import com.zhangzc.cloud.admin.service.MobileService;
import com.zhangzc.cloud.common.core.util.R;
import com.zhangzc.cloud.common.security.annotation.Inner;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 手机验证码
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/7/6 4:59 下午
 */
@RestController
@AllArgsConstructor
@RequestMapping("/mobile")
public class MobileController {

	private final MobileService mobileService;

	/**
	 * 发送短信验证码
	 * @param mobile 手机号
	 * @return
	 */
	@Inner(value = false)
	@GetMapping("/{mobile}")
	public R sendSmsCode(@PathVariable String mobile) {
		return mobileService.sendSmsCode(mobile);
	}

}
