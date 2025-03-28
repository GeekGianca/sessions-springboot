package com.example.session.app.usecase;

import com.example.session.core.domain.persistence.ISessionRepository;
import com.example.session.core.model.SessionEntity;
import com.example.session.core.usecase.IFindAllSessionUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This use case represents the list of active sessions,
 * showing all sessions without exclusion.
 */
@Service
public class FindAllSessionsUseCase implements IFindAllSessionUseCase {
    private final ISessionRepository sessionRepository;

    public FindAllSessionsUseCase(ISessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public List<SessionEntity> execute() {
        return sessionRepository.findAll();
    }
}
