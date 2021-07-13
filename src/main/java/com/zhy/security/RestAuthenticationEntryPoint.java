package com.zhy.security;

import cn.hutool.json.JSONUtil;
import com.zhy.model.dto.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhy
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestAuthenticationEntryPoint.class);


    @Override
    public void commence(HttpServletRequest req, HttpServletResponse rep, AuthenticationException e) throws IOException, ServletException {
        LOGGER.error(e.getMessage());
        rep.setCharacterEncoding("UTF-8");
        rep.setContentType("application/json");
        rep.getWriter().println(JSONUtil.toJsonStr(ApiResponse.bad(401, e.getMessage())));
        rep.getWriter().flush();
    }
}