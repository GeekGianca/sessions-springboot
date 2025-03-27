package com.example.session.app.usecase;

import com.example.session.core.domain.persistence.IUserRepository;
import com.example.session.core.model.UserEntity;
import com.example.session.core.usecase.IFindUserUseCase;
import org.springframework.stereotype.Service;

@Service
public class FindUserUseCase implements IFindUserUseCase {
    private final IUserRepository userRepository;

    public FindUserUseCase(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity findUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }
}
