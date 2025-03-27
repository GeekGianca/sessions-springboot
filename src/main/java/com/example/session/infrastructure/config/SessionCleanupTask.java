package com.example.session.infrastructure.config;

import com.example.session.core.usecase.ILogoutUseCase;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class SessionCleanupTask {

    private final ILogoutUseCase logoutUseCase;

    public SessionCleanupTask(ILogoutUseCase logoutUseCase) {
        this.logoutUseCase = logoutUseCase;
    }

    @Scheduled(fixedRate = 600000)
    public void cleanupExpiredSessions() {

    }
}
