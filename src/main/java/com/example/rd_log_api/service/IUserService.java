package com.example.rd_log_api.service;

import com.example.rd_log_api.domain.dto.UserDto;
import com.example.rd_log_api.domain.dto.LoginDto;
import com.example.rd_log_api.domain.dto.exception.NotFoundException;

import java.sql.Time;
import java.util.List;

public interface IUserService {

    UserDto createUser(UserDto user);
    List<UserDto> listUsers();
    UserDto getUser(Long id);
    UserDto updateUser(Long id, UserDto user);
    void deleteUser(Long id);
    LoginResponse loginUser(LoginDto loginDTO);
    UserDto userUpdate(Long id, UserDto user);

    UserDto updateUser(Long id, UserDto user, String cnpj, String phone_number, Time opining_hours, Time closing_hours) throws NotFoundException;
}
