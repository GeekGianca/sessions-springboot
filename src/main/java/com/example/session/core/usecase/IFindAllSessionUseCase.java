package com.example.session.core.usecase;

import com.example.session.core.model.SessionEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IFindAllSessionUseCase {
    List<SessionEntity> execute();
}
