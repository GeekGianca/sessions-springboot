package com.example.session.app.usecase;

import com.example.session.core.domain.ex.UserException;
import com.example.session.core.domain.persistence.ISessionRepository;
import com.example.session.core.domain.persistence.IUserRepository;
import com.example.session.core.model.SessionEntity;
import com.example.session.core.model.UserEntity;
import com.example.session.core.usecase.ICreateSessionUseCase;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

@Service
public class CreateSessionUseCase implements ICreateSessionUseCase {

    private final IUserRepository userRepository;
    private final ISessionRepository sessionRepository;
    private final SessionRegistry sessionRegistry;

    public CreateSessionUseCase(IUserRepository userRepository, ISessionRepository sessionRepository, SessionRegistry sessionRegistry) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.sessionRegistry = sessionRegistry;
    }

    @Override
    public SessionEntity execute() throws Throwable {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        UserEntity user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow((Supplier<Throwable>) () -> new UserException("User not found"));
        SessionEntity session = new SessionEntity();
        session.setSessionUuid(UUID.randomUUID().toString());
        session.setCreationTimestamp(System.currentTimeMillis());
        session.setUser(user);
        session.setSessionState(true);
        return sessionRepository.save(session);
    }

    @Override
    public SessionEntity execute(String username) throws Throwable {
        SessionInformation currentSession = sessionRegistry.getSessionInformation(username);
        UserEntity user = userRepository.findByEmail(username).orElseThrow((Supplier<Throwable>) () -> new UserException("User not found"));
        SessionEntity session = new SessionEntity();
        try {
            session.setSessionUuid(currentSession.getSessionId());
            session.setCreationTimestamp(Date.from(Instant.ofEpochMilli(currentSession.getLastRequest().getTime())).getTime());
        } catch (Exception e) {
            session.setSessionUuid(UUID.randomUUID().toString());
            session.setCreationTimestamp(System.currentTimeMillis());
        }
        session.setUser(user);
        session.setSessionState(true);
        return sessionRepository.save(session);
    }
}
