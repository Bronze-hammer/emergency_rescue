package com.china.rescue.framework.security.handler;

import com.china.rescue.framework.security.jwt.JWTProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 认证处理器
 */
@Configuration
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JWTProvider jwtProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.println(jwtProvider.createToken(authentication, true));
    }
}
