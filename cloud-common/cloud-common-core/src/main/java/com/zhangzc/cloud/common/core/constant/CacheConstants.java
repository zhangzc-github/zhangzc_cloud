package com.zhangzc.cloud.common.core.constant;

public interface CacheConstants {
    /**
     * 验证码前缀
     */
    String DEFAULT_CODE_KEY = "DEFAULT_CODE_KEY:";

    /**
     * 字典信息缓存
     */
    String DICT_DETAILS = "dict_details";

    /**
     * 菜单信息缓存
     */
    String MENU_DETAILS = "menu_details";

    /**
     * 用户信息缓存
     */
    String USER_DETAILS = "user_details";

    /**
     * oauth 客户端信息
     */
    String CLIENT_DETAILS_KEY = "pig_oauth:client:details";

    /**
     * 客户端配置缓存
     */
    String CLIENT_FLAG = "client_config_flag";

    /**
     * 全局缓存，在缓存名称上加上该前缀表示该缓存不区分租户，比如:
     * <p/>
     * {@code @Cacheable(value = CacheConstants.GLOBALLY+CacheConstants.MENU_DETAILS, key = "#roleId  + '_menu'", unless = "#result == null")}
     */
    String GLOBALLY = "gl:";

    /**
     * 租户缓存 (不区分租户)
     */
    String TENANT_DETAILS = GLOBALLY + "tenant_details";

    /**
     * 路由存放
     */
    String ROUTE_KEY = GLOBALLY + "gateway_route_key";

    /**
     * 内存reload 时间
     */
    String ROUTE_JVM_RELOAD_TOPIC = "gateway_jvm_route_reload_topic";

    /**
     * redis 重新加载 路由信息
     */
    String ROUTE_REDIS_RELOAD_TOPIC = "upms_redis_route_reload_topic";

}
