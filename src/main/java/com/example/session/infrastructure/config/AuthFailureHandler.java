package com.example.session.infrastructure.config;

import com.example.session.core.domain.persistence.IUserRepository;
import com.example.session.core.model.UserEntity;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;

public class AuthFailureHandler implements org.springframework.security.web.authentication.AuthenticationFailureHandler {
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthFailureHandler(IUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            UserEntity currentUser = userRepository.findByEmail(username).orElse(null);
            if (currentUser == null) {
                response.sendRedirect("/?notfound");
            } else {
                if (!passwordEncoder.matches(password, currentUser.getPassword())) {
                    response.sendRedirect("/?badpassword");
                }
                if (!currentUser.getIsEnable()) {
                    response.sendRedirect("/?disabled");
                }
            }
        } catch (Exception e) {
            response.sendRedirect("/?error");
        }
    }
}
