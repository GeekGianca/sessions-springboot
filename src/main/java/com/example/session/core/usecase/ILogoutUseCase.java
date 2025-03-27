package com.example.session.core.usecase;

import com.example.session.core.model.SessionEntity;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public interface ILogoutUseCase {
    SessionEntity execute(HttpServletRequest request) throws Throwable;
    SessionEntity execute() throws Throwable;
}
