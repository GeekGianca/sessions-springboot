package com.example.session.app.usecase;

import com.example.session.core.domain.ex.SessionException;
import com.example.session.core.domain.ex.UserException;
import com.example.session.core.domain.persistence.ISessionRepository;
import com.example.session.core.domain.persistence.IUserRepository;
import com.example.session.core.model.SessionEntity;
import com.example.session.core.model.UserEntity;
import com.example.session.core.usecase.ILogoutUseCase;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Supplier;

/**
 * This use case executes the logout function.
 * This can be done in two ways, but it has a single responsibility:
 * it can automatically log out either because the time has expired,
 * or it logs out because the user executed the logout function.
 */
@Service
public class LogoutUseCase implements ILogoutUseCase {

    private final IUserRepository userRepository;
    private final ISessionRepository sessionRepository;
    private final SessionRegistry sessionRegistry;

    public LogoutUseCase(IUserRepository userRepository, ISessionRepository sessionRepository, SessionRegistry sessionRegistry) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.sessionRegistry = sessionRegistry;
    }

    private UserDetails getUserDetails() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (UserDetails) auth.getPrincipal();
    }

    @Override
    public SessionEntity execute(HttpServletRequest request) throws Throwable {
        UserEntity user = userRepository.findByEmail(getUserDetails().getUsername()).orElseThrow((Supplier<Throwable>) () -> new UserException("User not found"));
        SessionEntity currentSession = sessionRepository.findCurrentSessionByUser(user.getId()).orElseThrow((Supplier<Throwable>) () -> new SessionException("Session not found"));
        currentSession.setSessionState(false);
        sessionRepository.save(currentSession);
        request.getSession().invalidate();
        SecurityContextHolder.clearContext();
        return currentSession;
    }

    @Override
    public SessionEntity execute() throws Throwable {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getPrincipal());
        UserDetails current = (UserDetails) auth.getPrincipal();
        UserEntity user = userRepository.findByEmail(current.getUsername()).orElseThrow((Supplier<Throwable>) () -> new UserException("User not found"));
        SessionEntity currentSession = sessionRepository.findCurrentSessionByUser(user.getId()).orElseThrow((Supplier<Throwable>) () -> new SessionException("Session not found"));
        currentSession.setSessionState(false);
        SessionEntity sessionEntity = sessionRepository.save(currentSession);
        for (SessionInformation session : sessionRegistry.getAllSessions(null, false)) {
            if (session.getLastRequest().getTime() + 30 * 60 * 1000 < System.currentTimeMillis()
                    && getUserDetails().getUsername() == session.getPrincipal()) {
                session.expireNow();
            }
        }
        SecurityContextHolder.clearContext();
        return sessionEntity;
    }
}
