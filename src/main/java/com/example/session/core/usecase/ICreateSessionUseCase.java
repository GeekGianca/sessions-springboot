package com.example.session.core.usecase;

import com.example.session.core.model.SessionEntity;
import org.springframework.stereotype.Service;

@Service
public interface ICreateSessionUseCase {
    SessionEntity execute() throws Throwable;
    SessionEntity execute(String username) throws Throwable;
}
