package com.zhangzc.cloud.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.plugin.excel.annotation.ResponseExcel;
import com.zhangzc.cloud.admin.service.SysLogService;
import com.zhangzc.cloud.common.core.util.R;
import com.zhangzc.cloud.common.security.annotation.Inner;
import com.zhangzc.cloud.upms.api.dto.SysLogDTO;
import com.zhangzc.cloud.upms.api.entity.SysLog;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
 * TODO
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/3/3 12:09 下午
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/log")
public class LogController {

	private final SysLogService sysLogService;

	/**
	 * 简单分页查询
	 * @param page 分页对象
	 * @param sysLog 系统日志
	 * @return
	 */
	@GetMapping("/page")
	public R<IPage<SysLog>> getLogPage(Page page, SysLogDTO sysLog) {
		return R.ok(sysLogService.getLogByPage(page, sysLog));
	}

	/**
	 * 删除日志
	 * @param id ID
	 * @return success/false
	 */
	@DeleteMapping("/{id:\\d+}")
	@PreAuthorize("@pms.hasPermission('sys_log_del')")
	public R<Boolean> removeById(@PathVariable Long id) {
		return R.ok(sysLogService.removeById(id));
	}

	/**
	 * 插入日志
	 * @param sysLog 日志实体
	 * @return success/false
	 */
	@Inner
	@PostMapping
	public R<Boolean> save(@Valid @RequestBody SysLog sysLog) {
		return R.ok(sysLogService.save(sysLog));
	}

	/**
	 * 导出excel 表格
	 * @param sysLog 查询条件
	 * @return EXCEL
	 */
	@ResponseExcel
	@GetMapping("/export")
	@PreAuthorize("@pms.hasPermission('sys_log_import_export')")
	public List<SysLog> export(SysLogDTO sysLog) {
		return sysLogService.getLogList(sysLog);
	}

}
