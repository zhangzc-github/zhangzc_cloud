package com.zhangzc.cloud.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangzc.cloud.upms.api.entity.SysOauthClientDetails;

/**
 * TODO
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/3/3 11:31 上午
 */
public interface SysOauthClientDetailsService extends IService<SysOauthClientDetails> {

	/**
	 * 通过ID删除客户端
	 * @param id
	 * @return
	 */
	Boolean removeClientDetailsById(String id);

	/**
	 * 修改客户端信息
	 * @param sysOauthClientDetails
	 * @return
	 */
	Boolean updateClientDetailsById(SysOauthClientDetails sysOauthClientDetails);

	/**
	 * 清除客户端缓存
	 */
	void clearClientCache();

}
