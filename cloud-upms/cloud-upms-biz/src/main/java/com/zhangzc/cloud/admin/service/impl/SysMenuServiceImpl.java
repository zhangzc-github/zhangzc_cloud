package com.zhangzc.cloud.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangzc.cloud.admin.mapper.SysMenuMapper;
import com.zhangzc.cloud.admin.service.SysMenuService;
import com.zhangzc.cloud.upms.api.entity.SysMenu;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * SysMenuServiceImpl
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/2/14 11:16 上午
 */
@Service
@RequiredArgsConstructor
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {
    private final SysMenuMapper sysMenuMapper;

    /**
     * 通过roleId查询菜单
     * @param roleId role id
     * @return Set<SysMenu>
     */
    @Override
    public Set<SysMenu> findMenuByRoleId(long roleId) {
        return sysMenuMapper.listMenusByRoleId(roleId);
    }
}
