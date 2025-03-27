package com.example.session.app.mapper;

import com.example.session.app.dto.UserDto;
import com.example.session.core.model.UserEntity;

public class UserMapper {
    public static UserDto mapToDto(UserEntity user) {
        return new UserDto(user.getId(),
                user.getNames(),
                user.getEmail(),
                user.getPassword(),
                user.getIsEnable(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                user.getDeletedAt());
    }
}
