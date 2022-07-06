package com.zhangzc.cloud.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangzc.cloud.upms.api.entity.SysTenant;

import java.util.List;

public interface SysTenantService extends IService<SysTenant> {

	/**
	 * 获取正常的租户
	 * @return
	 */
	List<SysTenant> getNormalTenant();

	/**
	 * 保存租户
	 * @param sysTenant
	 * @return
	 */
	Boolean saveTenant(SysTenant sysTenant);

}
