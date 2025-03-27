package com.example.session.core.domain.persistence;

import com.example.session.core.model.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ISessionRepository extends JpaRepository<SessionEntity, Long> {
    @Query(value = "SELECT * FROM sessions WHERE fk_user_id=:userId AND session_state = 1", nativeQuery = true)
    Optional<SessionEntity> findCurrentSessionByUser(Long userId);
}
