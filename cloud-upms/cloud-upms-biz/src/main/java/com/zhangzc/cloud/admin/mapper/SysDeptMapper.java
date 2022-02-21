package com.zhangzc.cloud.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhangzc.cloud.upms.api.entity.SysDept;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * SysDept Mapper接口
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/2/21 11:17 上午
 */
@Mapper
public interface SysDeptMapper extends BaseMapper<SysDept> {
    /**
     * 关联dept——relation
	 * @return 数据列表
	 */
    List<SysDept> listDepts();
}
