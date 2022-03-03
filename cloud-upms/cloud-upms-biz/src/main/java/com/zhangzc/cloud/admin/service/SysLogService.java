package com.zhangzc.cloud.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangzc.cloud.upms.api.dto.SysLogDTO;
import com.zhangzc.cloud.upms.api.entity.SysLog;

import java.util.List;

/**
 * TODO
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/3/3 11:43 上午
 */
public interface SysLogService extends IService<SysLog> {

	/**
	 * 分页查询日志
	 * @param page
	 * @param sysLog
	 * @return
	 */
	Page<SysLog> getLogByPage(Page page, SysLogDTO sysLog);

	/**
	 * 列表查询日志
	 * @param sysLog 查询条件
	 * @return List
	 */
	List<SysLog> getLogList(SysLogDTO sysLog);

}
