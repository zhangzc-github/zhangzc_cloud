package com.zhangzc.cloud.common.data.tenant;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class TenantBroker {
    @FunctionalInterface
    public interface RunAs<T> {
        /**
         * 执行业务逻辑
         * @param tenantId
         * @throws Exception
         */
        void run(T tenantId) throws Exception;
    }
    @FunctionalInterface
    public interface ApplyAs<T, R> {

        /**
         * 执行业务逻辑,返回一个值
         * @param tenantId
         * @return
         * @throws Exception
         */
        R apply(T tenantId) throws Exception;

    }

    public void runAs(Long tenant, RunAs<Long> func) {
        Long pre = TenantContextHolder.getTenantId();
        try {
            log.trace("TenantBroker 切换租户{} -> {}", pre, tenant);
            TenantContextHolder.setTenantId(tenant);
            func.run(tenant);
        }
        catch (Exception e) {
            throw new TenantBrokerExceptionWrapper(e.getMessage(), e);
        }
        finally {
            log.trace("TenantBroker 还原租户{} <- {}", pre, tenant);
            TenantContextHolder.setTenantId(pre);
        }
    }

    /**
     * 以某个租户的身份运行
     * @param tenant 租户ID
     * @param func
     * @param <T> 返回数据类型
     * @return
     */
    public <T> T applyAs(Long tenant, ApplyAs<Long, T> func) {
        final Long pre = TenantContextHolder.getTenantId();
        try {
            log.trace("TenantBroker 切换租户{} -> {}", pre, tenant);
            TenantContextHolder.setTenantId(tenant);
            return func.apply(tenant);
        } catch (Exception e) {
            throw new TenantBrokerExceptionWrapper(e.getMessage(), e);
        } finally {
            log.trace("TenantBroker 还原租户{} <- {}", pre, tenant);
            TenantContextHolder.setTenantId(pre);
        }
    }

    public class TenantBrokerExceptionWrapper extends RuntimeException {

        public TenantBrokerExceptionWrapper(String message, Throwable cause) {
            super(message, cause);
        }

        public TenantBrokerExceptionWrapper(Throwable cause) {
            super(cause);
        }

    }
}
