package com.example.session.app.usecase;

import com.example.session.core.domain.ex.UserException;
import com.example.session.core.domain.persistence.IUserRepository;
import com.example.session.core.model.UserEntity;
import com.example.session.core.usecase.ISaveUserUseCase;
import org.springframework.stereotype.Service;

/**
 * Use case used to create a user.
 */
@Service
public class SaveUserUseCase implements ISaveUserUseCase {
    private final IUserRepository userRepository;

    public SaveUserUseCase(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity execute(UserEntity user) {
        UserEntity userExist = userRepository.findByEmail(user.getEmail()).orElse(null);
        if (userExist != null) {
            throw new UserException("User already exists");
        }
        return userRepository.save(user);
    }
}
