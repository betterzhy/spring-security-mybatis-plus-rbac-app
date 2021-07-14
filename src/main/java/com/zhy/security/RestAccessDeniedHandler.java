package com.zhy.security;

import cn.hutool.json.JSONUtil;
import com.zhy.model.dto.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhy
 */
@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestAccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest req, HttpServletResponse rep, AccessDeniedException e) throws IOException, ServletException {
        LOGGER.error(e.getMessage());
        rep.setCharacterEncoding("UTF-8");
        rep.setContentType("application/json");
        rep.getWriter().println(JSONUtil.toJsonStr(ApiResponse.forbidden(e.getMessage())));
        rep.getWriter().flush();
    }
}
