package com.example.session.app.mapper;

import com.example.session.app.dto.SessionDto;
import com.example.session.core.model.SessionEntity;

public class SessionMapper {
    public static SessionDto sessionToSessionDto(SessionEntity session) {
        return new SessionDto(session.getId(), session.getSessionUuid(), session.getCreationTimestamp(), session.isSessionState(), UserMapper.mapToDto(session.getUser()));
    }
}
