package com.example.session.core.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "SESSIONS")
public class SessionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "session_uuid", nullable = false, unique = true)
    private String sessionUuid;

    @Column(name = "creation_timestamp", nullable = false)
    private long creationTimestamp;

    @Column(name = "session_state", nullable = false)
    private boolean sessionState;

    @ManyToOne
    @JoinColumn(name = "fk_user_id", referencedColumnName = "id", nullable = false)
    private UserEntity user;
}
