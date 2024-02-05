package com.example.ProjectTwo.controller;

import com.example.ProjectTwo.dto.UserDto;
import com.example.ProjectTwo.entity.UserEntity;
import com.example.ProjectTwo.mapper.UserMapper;
import com.example.ProjectTwo.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {
private UserRepository userRepository;
private UserMapper userMapper;

    public UserController(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }
    @PostMapping("/")
    public ResponseEntity<Void> addUser(@RequestBody UserDto userDto) {
        userRepository.save(userMapper.userDtoToUserEntity(userDto));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getPostId(@PathVariable Long id) {
        return new ResponseEntity<>(
                userMapper.userEntityToUserDto(userRepository.findById(id).get()),
                HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> allUser() {
        return new ResponseEntity<>(
                userMapper.userEntitysToUserDtos(userRepository.findAll()),
                HttpStatus.OK);
    }

}
