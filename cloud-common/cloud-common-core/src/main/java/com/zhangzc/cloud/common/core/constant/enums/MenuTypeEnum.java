package com.zhangzc.cloud.common.core.constant.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 菜单类型
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/2/14 10:52 上午
 */
@Getter
@RequiredArgsConstructor
public enum MenuTypeEnum {

    /**
     * 左侧菜单
     */
    LEFT_MENU("0", "left"),

    /**
     * 顶部菜单
     */
    TOP_MENU("2", "top"),

    /**
     * 按钮
     */
    BUTTON("1", "button");

    /**
     * 类型
     */
    private final String type;

    /**
     * 描述
     */
    private final String description;

}
