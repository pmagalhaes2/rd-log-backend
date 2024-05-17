package com.example.rd_log_api.controllers;

import com.example.rd_log_api.domain.dto.LoginDto;
import com.example.rd_log_api.domain.dto.UserDto;
import com.example.rd_log_api.service.IUserService;
import com.example.rd_log_api.service.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final IUserService service;

    @Autowired
    public UserController(IUserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createUser(user));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> listUsers() {
        return ResponseEntity.ok(service.listUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getUser(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> userUpdate(
            @PathVariable("id") Long id,
            @RequestBody UserDto user
    ) {
        UserDto userUpdated = service.userUpdate(id, user);
        if (userUpdated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(userUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}