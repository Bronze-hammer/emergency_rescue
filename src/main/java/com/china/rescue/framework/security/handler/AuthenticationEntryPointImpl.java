package com.china.rescue.framework.security.handler;

import com.alibaba.fastjson.JSON;
import com.china.rescue.common.ResponseCode;
import com.china.rescue.common.ServerResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException {
        response.setStatus(200);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(JSON.toJSONString(
                ServerResponse.createByErrorCodeMessage(ResponseCode.UNAUTHORIZED.getCode(),
                        "请求访问：" + request.getRequestURI() + "," + ResponseCode.UNAUTHORIZED.getDesc())
        ));

    }
}
