package com.zhangzc.cloud.admin.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhangzc.cloud.admin.service.SysTenantService;
import com.zhangzc.cloud.common.core.constant.CacheConstants;
import com.zhangzc.cloud.common.core.util.R;
import com.zhangzc.cloud.common.security.annotation.Inner;
import com.zhangzc.cloud.upms.api.entity.SysTenant;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 租户管理
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/7/1 12:23 下午
 */
@RestController
@AllArgsConstructor
@RequestMapping("/tenant")
@Tag(name = "租户管理")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class TenantController {

	private final SysTenantService sysTenantService;

	/**
	 * 分页查询
	 * @param page 分页对象
	 * @param sysTenant 租户
	 * @return
	 */
	@GetMapping("/page")
	public R getSysTenantPage(Page page, SysTenant sysTenant) {
		return R.ok(sysTenantService.page(page, Wrappers.query(sysTenant)));
	}

	/**
	 * 通过id查询租户
	 * @param id id
	 * @return R
	 */
	@GetMapping("/{id}")
	public R getById(@PathVariable("id") Integer id) {
		return R.ok(sysTenantService.getById(id));
	}

	/**
	 * 新增租户
	 * @param sysTenant 租户
	 * @return R
	 */
	@PostMapping
	@PreAuthorize("@pms.hasPermission('admin_systenant_add')")
	public R save(@RequestBody SysTenant sysTenant) {
		return R.ok(sysTenantService.saveTenant(sysTenant));
	}

	/**
	 * 修改租户
	 * @param sysTenant 租户
	 * @return R
	 */
	@PutMapping
	@PreAuthorize("@pms.hasPermission('admin_systenant_edit')")
	public R updateById(@RequestBody SysTenant sysTenant) {
		return R.ok(sysTenantService.updateById(sysTenant));
	}

	/**
	 * 通过id删除租户
	 * @param id id
	 * @return R
	 */
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('admin_systenant_del')")
	public R removeById(@PathVariable Integer id) {
		return R.ok(sysTenantService.removeById(id));
	}

	/**
	 * 查询全部有效的租户
	 * @return
	 */
	@Inner(value = false)
	@GetMapping("/list")
	public R list() {
		List<SysTenant> tenants = sysTenantService.getNormalTenant().stream()
				.filter(tenant -> tenant.getStartTime().isBefore(LocalDateTime.now()))
				.filter(tenant -> tenant.getEndTime().isAfter(LocalDateTime.now())).collect(Collectors.toList());
		return R.ok(tenants);
	}

}
