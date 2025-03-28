package com.example.session.app.usecase;

import com.example.session.core.domain.persistence.IUserRepository;
import com.example.session.core.model.UserEntity;
import com.example.session.core.usecase.IFindUserUseCase;
import org.springframework.stereotype.Service;

/**
 * Use case used to search for a user by ID,
 * this user will have the list of sessions in its relationship.
 */
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
