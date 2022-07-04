package com.zhangzc.cloud.common.data.tenant;

import com.alibaba.ttl.TransmittableThreadLocal;
import lombok.experimental.UtilityClass;

/**
 * 租户工具类
 * @version 1.0
 * @author Zhichao Zhang
 * @date 2022/7/4 3:24 下午
 */
@UtilityClass
public class TenantContextHolder {
    private final ThreadLocal<Long> THREAD_LOCAL_TENANT = new TransmittableThreadLocal<>();

    /**
     * TTL 设置租户ID<br/>
     * <b>谨慎使用此方法,避免嵌套调用。尽量使用 {@code TenantBroker} </b>
     * @param tenantId
     * @see TenantBroker
     */
    public void setTenantId(Long tenantId) {
        THREAD_LOCAL_TENANT.set(tenantId);
    }

    /**
     * 获取TTL中的租户ID
     * @return
     */
    public Long getTenantId() {
        return THREAD_LOCAL_TENANT.get();
    }

    public void clear() {
        THREAD_LOCAL_TENANT.remove();
    }
}
