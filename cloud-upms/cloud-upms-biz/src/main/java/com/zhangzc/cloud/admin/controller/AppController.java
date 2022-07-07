package com.zhangzc.cloud.admin.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zhangzc.cloud.admin.service.AppService;
import com.zhangzc.cloud.admin.service.SysUserService;
import com.zhangzc.cloud.common.core.util.R;
import com.zhangzc.cloud.common.security.annotation.Inner;
import com.zhangzc.cloud.upms.api.dto.UserInfo;
import com.zhangzc.cloud.upms.api.entity.SysUser;
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
@RequestMapping("/app")
public class AppController {

	private final AppService appService;

	private final SysUserService userService;

	/**
	 * 发送短信验证码
	 * @param mobile 手机号
	 * @return
	 */
	@Inner(value = false)
	@GetMapping("/{mobile}")
	public R sendSmsCode(@PathVariable String mobile) {
		return appService.sendSmsCode(mobile);
	}

	/**
	 * 获取指定用户全部信息
	 * @param phone 手机号
	 * @return 用户信息
	 */
	@Inner
	@GetMapping("/info/{phone}")
	public R<UserInfo> infoByMobile(@PathVariable String phone) {
		SysUser user = userService.getOne(Wrappers.<SysUser>query().lambda().eq(SysUser::getPhone, phone));
		if (user == null) {
			return R.failed(String.format("用户信息为空 %s", phone));
		}
		return R.ok(userService.getUserInfo(user));
	}

}
