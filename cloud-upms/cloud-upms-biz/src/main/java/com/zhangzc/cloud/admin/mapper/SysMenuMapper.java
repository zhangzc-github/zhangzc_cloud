package com.zhangzc.cloud.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhangzc.cloud.upms.api.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import java.util.Set;

/**
 * SysMenu Mapper接口
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/2/14 11:14 上午
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    /**
     * 通过roleId查询菜单
     * @param roleId role id
     * @return Menus
     */
    Set<SysMenu> listMenusByRoleId(Long roleId);
}
