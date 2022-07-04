package com.zhangzc.cloud.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangzc.cloud.upms.api.dto.SysOauthClientDetailsDTO;
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
	 * @param sysOauthClientDetailsDTO
	 * @return
	 */
	Boolean updateClientDetailsById(SysOauthClientDetailsDTO sysOauthClientDetailsDTO);

	/**
	 * 分页查询客户端信息
	 * @param page
	 * @param query
	 * @return
	 */
	Page queryPage(Page page, SysOauthClientDetails query);

	/**
	 * 添加客户端信息
	 * @param clientDetailsDTO
	 * @return
	 */
	Boolean saveClient(SysOauthClientDetailsDTO clientDetailsDTO);

}
