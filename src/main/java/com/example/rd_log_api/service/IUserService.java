package com.example.rd_log_api.service;

import com.example.rd_log_api.domain.dto.UserDto;
import com.example.rd_log_api.domain.dto.LoginDto;

import java.util.List;

public interface IUserService {

    UserDto createUser(UserDto user);
    List<UserDto> listUsers();
    UserDto getUser(int id);
    UserDto updateUser(int id, UserDto user);
    void deleteUser(int id);
    LoginResponse loginUser(LoginDto loginDTO);
    UserDto userUpdate(int id, UserDto user);
}
