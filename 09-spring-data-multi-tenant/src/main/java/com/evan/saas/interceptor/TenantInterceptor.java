package com.evan.saas.interceptor;

import com.evan.saas.context.TenantContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class TenantInterceptor implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String tenant = request.getParameter("tenant");
        if(StringUtils.isBlank(tenant)){
            response.sendRedirect("/login.html");
            return false;
        }else{
            TenantContextHolder.setTenant(tenant);
            return true;
        }
    }
}
