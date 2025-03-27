package com.example.session.core.domain.service;

import com.example.session.app.dto.UserSignUpDto;
import com.example.session.core.model.UserEntity;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {
    UserEntity create(UserSignUpDto userSignUpDto);

    UserEntity findUser(Long id);
}
