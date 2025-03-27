package com.example.session.core.domain.persistence;

import com.example.session.core.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {
    @Query(value = "SELECT * FROM users WHERE email=? AND deleted_at IS NULL", nativeQuery = true)
    Optional<UserEntity> findByEmail(String email);
}
