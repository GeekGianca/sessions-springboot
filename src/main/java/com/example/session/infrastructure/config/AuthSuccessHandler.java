package com.example.session.infrastructure.config;

import com.example.session.core.usecase.ICreateSessionUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class AuthSuccessHandler implements AuthenticationSuccessHandler {
    private final ICreateSessionUseCase createSessionUseCase;

    public AuthSuccessHandler(ICreateSessionUseCase createSessionUseCase) {
        this.createSessionUseCase = createSessionUseCase;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String username = request.getParameter("username");
        try {
            createSessionUseCase.execute(username);
            response.sendRedirect("/home");
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
