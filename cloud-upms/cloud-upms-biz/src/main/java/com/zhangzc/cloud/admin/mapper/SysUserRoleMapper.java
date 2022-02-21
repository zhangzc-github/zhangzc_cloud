package com.zhangzc.cloud.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhangzc.cloud.upms.api.entity.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * SysUserRole Mapper接口
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/2/12 3:34 下午
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
    /**
     * 根据用户Id删除该用户的角色关系
     * @param userId user id
     * @return boolean
     */
    Boolean deleteByUserId(@Param("userId") Long userId);
}
