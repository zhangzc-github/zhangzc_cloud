package com.zhangzc.cloud.admin.service;

import com.zhangzc.cloud.common.core.util.R;

public interface MobileService {

	/**
	 * 发送手机验证码
	 * @param mobile mobile
	 * @return code
	 */
	R<Boolean> sendSmsCode(String mobile);

}
