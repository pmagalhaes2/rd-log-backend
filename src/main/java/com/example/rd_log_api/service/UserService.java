package com.example.rd_log_api.service;

import com.example.rd_log_api.domain.dto.LoginDto;
import com.example.rd_log_api.domain.dto.UserDto;
import com.example.rd_log_api.domain.entities.User;
import com.example.rd_log_api.domain.mappers.UserMapper;
import com.example.rd_log_api.repositories.IUserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{
    private final IUserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(UserDto user) {
        User newUser = UserMapper.toEntity(user);
        String passwordEncoded = passwordEncoder.encode(user.getPassword());
        newUser.setPassword(passwordEncoded);
        return UserMapper.toDto(repository.save(newUser));
    }

    @Override
    public List<UserDto> listUsers() {
        return repository.findAll().stream().map(UserMapper::toDto).toList();
    }

    @Override
    public UserDto getUser(int id) {
        return repository.findById(id)
                .map(UserMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }

    @Override
    public UserDto updateUser(int id, UserDto user) {
        User userExistent = repository.findById(id).orElse(null);
        if (userExistent != null) {
            userExistent.setEmail(user.getEmail());
            String passwordEncoded = passwordEncoder.encode(user.getPassword());
            userExistent.setPassword(passwordEncoded);
            return UserMapper.toDto(repository.save(userExistent));
        }
        return null;
    }


    @Override
    public void deleteUser(int id) {
        repository.deleteById(id);
    }

    @Override
    public LoginResponse loginUser(LoginDto loginDTO) {
        String email = loginDTO.getUsername();
        String password = loginDTO.getPassword();

        User user = repository.findByEmail(email);

        if (user != null) {
            String passwordEncoded = user.getPassword();
            if (passwordEncoder.matches(password, passwordEncoded)) {
                return new LoginResponse("Login bem-sucedido", true);
            } else {
                return new LoginResponse("Senha incorreta", false);
            }
        } else {
            return new LoginResponse("Usuário não encontrado", false);
        }
    }

    @Override
    public UserDto userUpdate(int id, UserDto user) {
        return null;
    }
}
