package com.example.session.infrastructure.config;

import com.example.session.core.domain.service.ISessionService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.io.IOException;

public class LogoutExpiredHandler implements LogoutSuccessHandler {
    private final ISessionService sessionService;

    public LogoutExpiredHandler(ISessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        try {
            sessionService.logout(request);
            response.sendRedirect("/");
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
