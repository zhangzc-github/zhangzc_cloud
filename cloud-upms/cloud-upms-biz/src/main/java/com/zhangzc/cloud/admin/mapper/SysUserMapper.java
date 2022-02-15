package com.zhangzc.cloud.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhangzc.cloud.upms.api.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * SysUser Mapper接口
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/2/12 3:33 下午
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
}
