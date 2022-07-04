package com.zhangzc.cloud.common.data.tenant;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class TenantBroker {
    @FunctionalInterface
    public interface RunAs<T> {
        void run(T tenantId) throws Exception;
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

    public class TenantBrokerExceptionWrapper extends RuntimeException {

        public TenantBrokerExceptionWrapper(String message, Throwable cause) {
            super(message, cause);
        }

        public TenantBrokerExceptionWrapper(Throwable cause) {
            super(cause);
        }

    }
}
