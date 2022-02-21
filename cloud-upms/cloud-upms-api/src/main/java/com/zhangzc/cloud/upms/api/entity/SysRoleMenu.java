package com.zhangzc.cloud.upms.api.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * TODO
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/2/21 11:14 上午
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysRoleMenu extends Model<SysRoleMenu> {
    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    private Long roleId;
    /**
     * 菜单id
     */
    private Long menuId;
}
