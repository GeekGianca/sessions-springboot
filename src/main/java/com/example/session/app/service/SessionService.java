package com.example.session.app.service;

import com.example.session.core.domain.service.ISessionService;
import com.example.session.core.model.SessionEntity;
import com.example.session.core.usecase.ICreateSessionUseCase;
import com.example.session.core.usecase.IFindAllSessionUseCase;
import com.example.session.core.usecase.ILogoutUseCase;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The session service implements an interface
 * to segregate responsibilities, thus injecting the interface, not the class.
 * It fulfills the obligation to work only on the session,
 * in this case by creating a session or closing it.
 */
@Service
public class SessionService implements ISessionService {

    private final ICreateSessionUseCase createSessionUseCase;
    private final ILogoutUseCase logoutUseCase;
    private final IFindAllSessionUseCase findAllSessionUseCase;

    public SessionService(ICreateSessionUseCase createSessionUseCase, ILogoutUseCase logoutUseCase, IFindAllSessionUseCase findAllSessionUseCase) {
        this.createSessionUseCase = createSessionUseCase;
        this.logoutUseCase = logoutUseCase;
        this.findAllSessionUseCase = findAllSessionUseCase;
    }

    @Override
    public SessionEntity create() throws Throwable {
        return createSessionUseCase.execute();
    }

    @Override
    public void logout(HttpServletRequest request) throws Throwable {
        logoutUseCase.execute(request);
    }

    @Override
    public List<SessionEntity> getSessions() {
        return findAllSessionUseCase.execute();
    }
}
