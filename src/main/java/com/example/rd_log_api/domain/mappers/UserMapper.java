package com.example.rd_log_api.domain.mappers;

import com.example.rd_log_api.domain.dto.UserDto;
import com.example.rd_log_api.domain.entities.User;

public class UserMapper {

    public static User toEntity(UserDto dto){
        return User.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .build();
    }

    public static UserDto toDto(User entity){
        return UserDto.builder()
                .username(entity.getUsername())
                .password(entity.getPassword())
                .id(entity.getId())
                .build();
    }
}

