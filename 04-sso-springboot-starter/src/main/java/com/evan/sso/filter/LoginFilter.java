package com.evan.sso.filter;

import com.evan.sso.config.SSOConfig;
import com.evan.sso.dto.UserDTO;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.apache.commons.lang.StringUtils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class LoginFilter implements Filter {

    private static Cache<String, UserDTO> cache =
            CacheBuilder.newBuilder().maximumSize(10000)
                    .expireAfterWrite(3, TimeUnit.MINUTES).build();

    private SSOConfig ssoConfig;


    public LoginFilter(SSOConfig ssoConfig) {
        this.ssoConfig = ssoConfig;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        String token = request.getParameter("token");
        if(StringUtils.isBlank(token)) {
            Cookie[] cookies = request.getCookies();
            if(cookies!=null) {
                for(Cookie c : cookies) {
                    if(c.getName().equals("token")) {
                        token = c.getValue();
                    }
                }
            }
        }

        UserDTO userDTO = null;
        if(StringUtils.isNotBlank(token)) {
            userDTO = cache.getIfPresent(token);
            if(userDTO==null && !StringUtils.isEmpty(ssoConfig.getUsername())) {
                userDTO = mockAuthenticationServer(token, ssoConfig.getUsername(), ssoConfig.getPassword());
                if(userDTO!=null) {
                    cache.put(token, userDTO);
                }
            }
        }

        if(userDTO==null) {
            // 跳转到zuul 接口
            response.sendRedirect("http://localhost:9998/user/index");
            return;
        }


        chain.doFilter(request, response);
    }

    private UserDTO mockAuthenticationServer(String token, String userName, String password) {

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("evan");
        userDTO.setPassword("123456");
        return userDTO;

    }

    public SSOConfig getSsoConfig() {
        return ssoConfig;
    }

    public void setSsoConfig(SSOConfig ssoConfig) {
        this.ssoConfig = ssoConfig;
    }

    @Override
    public void destroy() {

    }
}
