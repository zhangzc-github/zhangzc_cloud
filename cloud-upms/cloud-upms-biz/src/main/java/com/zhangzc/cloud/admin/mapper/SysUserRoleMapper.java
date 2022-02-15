package com.zhangzc.cloud.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhangzc.cloud.upms.api.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * SysUserRole Mapper接口
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/2/12 3:34 下午
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRoleMapper> {
    /**
     * 通过user id查询用户角色信息
     * @param userId user id
     * @return 用户角色信息
     */
    List<SysRole> listRolesByUserId(Long userId);
}
