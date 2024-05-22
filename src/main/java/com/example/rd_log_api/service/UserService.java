package com.example.rd_log_api.service;

import com.example.rd_log_api.domain.dto.LoginDto;
import com.example.rd_log_api.domain.dto.UserDto;
import com.example.rd_log_api.domain.dto.exception.NotFoundException;
import com.example.rd_log_api.domain.entities.User;
import com.example.rd_log_api.domain.mappers.UserMapper;
import com.example.rd_log_api.repositories.IUserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
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
    public UserDto getUser(Long id) {
        return repository.findById(id)
                .map(UserMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não localizado pelo ID" + id));
    }

    @Override
    public UserDto updateUser(Long id, UserDto user) {
        return null;
    }


    @Override
    public void deleteUser(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Usuário não vinculado ao ID" + id);
        }
    }

    @Override
    public LoginResponse loginUser(LoginDto loginDTO) {
        String email = loginDTO.getEmail();
        String password = loginDTO.getPassword();

        User user = repository.findByEmail(email);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return new LoginResponse("Login realizado com sucesso.", user.getId(), user.getName());
        } else {
            return new LoginResponse("Dados incorretos, tente novamente.", null, null);
        }
    }

    @Override
    public UserDto userUpdate(Long id, UserDto user) {
        return null;
    }

    @Override
    public UserDto updateUser(Long id, UserDto user, String cnpj, String phone_number, Time opining_hours, Time closing_hours) throws NotFoundException {
        User userExistent = repository.findById(id).orElse(null);
        if (userExistent != null) {
            userExistent.setName(user.getName());
            userExistent.setEmail(user.getEmail());
            String passwordEncoded = passwordEncoder.encode(user.getPassword());
            userExistent.setPassword(passwordEncoded);
            return UserMapper.toDto(repository.save(userExistent));
        }
        return null;
    }
}
