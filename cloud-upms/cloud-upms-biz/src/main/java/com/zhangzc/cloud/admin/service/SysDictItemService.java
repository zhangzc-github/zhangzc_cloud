package com.zhangzc.cloud.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangzc.cloud.upms.api.entity.SysDictItem;

/**
 * TODO
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/3/3 11:31 上午
 */
public interface SysDictItemService extends IService<SysDictItem> {

	/**
	 * 删除字典项
	 * @param id 字典项ID
	 * @return
	 */
	void removeDictItem(Long id);

	/**
	 * 更新字典项
	 * @param item 字典项
	 * @return
	 */
	void updateDictItem(SysDictItem item);

}
