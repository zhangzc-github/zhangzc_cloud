package com.zhangzc.cloud.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangzc.cloud.upms.api.entity.SysDict;

/**
 * TODO
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/3/3 11:32 上午
 */
public interface SysDictService extends IService<SysDict> {

	/**
	 * 根据ID 删除字典
	 * @param id
	 * @return
	 */
	void removeDict(Long id);

	/**
	 * 更新字典
	 * @param sysDict 字典
	 * @return
	 */
	void updateDict(SysDict sysDict);

	/**
	 * 清除缓存
	 */
	void clearDictCache();

}
