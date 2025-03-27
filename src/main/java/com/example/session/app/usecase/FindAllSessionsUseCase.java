package com.example.session.app.usecase;

import com.example.session.core.domain.persistence.ISessionRepository;
import com.example.session.core.model.SessionEntity;
import com.example.session.core.usecase.IFindAllSessionUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

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
