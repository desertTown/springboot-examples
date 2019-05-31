package com.evan.saas.tenant.config;

import com.evan.saas.context.TenantContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;


public class CurrentTenantIdentifierResolverImpl implements CurrentTenantIdentifierResolver {

    /**
     * 默认的租户ID
     */
    private static final String DEFAULT_TENANT = "evan_tenant";

    /**
     * 解析当前租户的ID
     * @return
     */
    @Override
    public String resolveCurrentTenantIdentifier() {
        //通过租户上下文获取租户ID，此ID是用户登录时在header中进行设置的
        String tenant = TenantContextHolder.getTenant();
        //如果上下文中没有找到该租户ID，则使用默认的租户ID，或者直接报异常信息
        return StringUtils.isNotBlank(tenant)?tenant:DEFAULT_TENANT;
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
