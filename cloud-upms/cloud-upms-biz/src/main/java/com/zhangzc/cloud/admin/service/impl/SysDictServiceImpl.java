package com.zhangzc.cloud.admin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangzc.cloud.admin.mapper.SysDictItemMapper;
import com.zhangzc.cloud.admin.mapper.SysDictMapper;
import com.zhangzc.cloud.admin.service.SysDictService;
import com.zhangzc.cloud.common.core.constant.CacheConstants;
import com.zhangzc.cloud.common.core.constant.enums.DictTypeEnum;
import com.zhangzc.cloud.upms.api.entity.SysDict;
import com.zhangzc.cloud.upms.api.entity.SysDictItem;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * TODO
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/3/3 12:06 下午
 */
@Service
@RequiredArgsConstructor
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements SysDictService {

	private final SysDictItemMapper dictItemMapper;

	/**
	 * 根据ID 删除字典
	 * @param id 字典ID
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	@CacheEvict(value = CacheConstants.DICT_DETAILS, allEntries = true)
	public void removeDict(Long id) {
		SysDict dict = this.getById(id);
		// 系统内置
		Assert.state(!DictTypeEnum.SYSTEM.getType().equals(dict.getSystemFlag()), "系统内置字典项目不能删除");
		baseMapper.deleteById(id);
		dictItemMapper.delete(Wrappers.<SysDictItem>lambdaQuery().eq(SysDictItem::getDictId, id));
	}

	/**
	 * 更新字典
	 * @param dict 字典
	 * @return
	 */
	@Override
	@CacheEvict(value = CacheConstants.DICT_DETAILS, key = "#dict.type")
	public void updateDict(SysDict dict) {
		SysDict sysDict = this.getById(dict.getId());
		// 系统内置
		Assert.state(!DictTypeEnum.SYSTEM.getType().equals(sysDict.getSystemFlag()), "系统内置字典项目不能修改");
		this.updateById(dict);
	}

	@Override
	@CacheEvict(value = CacheConstants.DICT_DETAILS, allEntries = true)
	public void clearDictCache() {

	}

}
