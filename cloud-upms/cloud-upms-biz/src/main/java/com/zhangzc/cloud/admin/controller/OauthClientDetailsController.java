package com.zhangzc.cloud.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhangzc.cloud.admin.service.SysOauthClientDetailsService;
import com.zhangzc.cloud.common.core.util.R;
import com.zhangzc.cloud.upms.api.entity.SysOauthClientDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
 * TODO
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/3/3 12:29 下午
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
public class OauthClientDetailsController {

	private final SysOauthClientDetailsService sysOauthClientDetailsService;

	/**
	 * 通过ID查询
	 * @param clientId 客户端id
	 * @return SysOauthClientDetails
	 */
	@GetMapping("/{clientId}")
	public R<List<SysOauthClientDetails>> getByClientId(@PathVariable String clientId) {
		return R.ok(sysOauthClientDetailsService
				.list(Wrappers.<SysOauthClientDetails>lambdaQuery().eq(SysOauthClientDetails::getClientId, clientId)));
	}

	/**
	 * 简单分页查询
	 * @param page 分页对象
	 * @param sysOauthClientDetails 系统终端
	 * @return
	 */
	@GetMapping("/page")
	public R<IPage<SysOauthClientDetails>> getOauthClientDetailsPage(Page page,
			SysOauthClientDetails sysOauthClientDetails) {
		return R.ok(sysOauthClientDetailsService.page(page, Wrappers.query(sysOauthClientDetails)));
	}

	/**
	 * 添加
	 * @param sysOauthClientDetails 实体
	 * @return success/false
	 */
	@PostMapping
	@PreAuthorize("@pms.hasPermission('sys_client_add')")
	public R<Boolean> add(@Valid @RequestBody SysOauthClientDetails sysOauthClientDetails) {
		return R.ok(sysOauthClientDetailsService.save(sysOauthClientDetails));
	}

	/**
	 * 删除
	 * @param id ID
	 * @return success/false
	 */
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('sys_client_del')")
	public R<Boolean> removeById(@PathVariable String id) {
		return R.ok(sysOauthClientDetailsService.removeClientDetailsById(id));
	}

	/**
	 * 编辑
	 * @param sysOauthClientDetails 实体
	 * @return success/false
	 */
	@PutMapping
	@PreAuthorize("@pms.hasPermission('sys_client_edit')")
	public R<Boolean> update(@Valid @RequestBody SysOauthClientDetails sysOauthClientDetails) {
		return R.ok(sysOauthClientDetailsService.updateClientDetailsById(sysOauthClientDetails));
	}

	@DeleteMapping("/cache")
	@PreAuthorize("@pms.hasPermission('sys_client_del')")
	public R clearClientCache() {
		sysOauthClientDetailsService.clearClientCache();
		return R.ok();
	}

}
