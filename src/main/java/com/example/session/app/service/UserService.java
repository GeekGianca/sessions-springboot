package com.example.session.app.service;

import com.example.session.app.dto.UserSignUpDto;
import com.example.session.core.domain.service.IUserService;
import com.example.session.core.model.UserEntity;
import com.example.session.core.usecase.ISaveUserUseCase;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    private final PasswordEncoder passwordEncoder;
    private final ISaveUserUseCase saveUserUseCase;

    public UserService(PasswordEncoder passwordEncoder, ISaveUserUseCase saveUserUseCase) {
        this.passwordEncoder = passwordEncoder;
        this.saveUserUseCase = saveUserUseCase;
    }

    @Override
    public UserEntity create(UserSignUpDto userSignUpDto) {
        return saveUserUseCase.execute(userSignUpDto.toUser(passwordEncoder));
    }
}
