package com.example.session.core.usecase;

import com.example.session.core.model.UserEntity;
import org.springframework.stereotype.Service;

@Service
public interface ISaveUserUseCase {
    UserEntity execute(UserEntity user);
}
