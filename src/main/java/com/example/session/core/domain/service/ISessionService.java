package com.example.session.core.domain.service;

import com.example.session.core.model.SessionEntity;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ISessionService {
    SessionEntity create() throws Throwable;

    void logout(HttpServletRequest request) throws Throwable;

    List<SessionEntity> getSessions();
}
