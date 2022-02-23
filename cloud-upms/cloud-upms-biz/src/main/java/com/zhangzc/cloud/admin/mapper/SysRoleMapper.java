package com.zhangzc.cloud.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhangzc.cloud.upms.api.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * SysRole Mapper接口
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/2/23 10:21 上午
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
    /**
     * 通过user id查询用户角色信息
     * @param userId user id
     * @return 用户角色信息
     */
    List<SysRole> listRolesByUserId(Long userId);
}
