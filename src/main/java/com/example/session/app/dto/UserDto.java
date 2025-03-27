package com.example.session.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String names;
    private String email;
    private String password;
    private Boolean isEnable;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private List<SessionDto> sessions;

    public String isActive() {
        SessionDto current = sessions.stream().filter(SessionDto::isSessionState).toList().stream().findFirst().orElse(null);
        return current == null ? "Offline" : "Online";
    }

    public int sizeSessions() {
        return sessions.stream().filter(SessionDto::isSessionState).toList().size();
    }
}
