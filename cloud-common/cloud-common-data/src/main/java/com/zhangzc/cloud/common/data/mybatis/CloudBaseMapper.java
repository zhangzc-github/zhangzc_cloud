package com.zhangzc.cloud.common.data.mybatis;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface CloudBaseMapper<T> extends BaseMapper<T> {
    /**
     * 批量插入 仅适用于 mysql
     * @param entityList 实体列表
     * @return 影响行数
     */
    Integer insertBatchSomeColumn(List<T> entityList);
}
