package com.example.ProjectTwo.controller;

import com.example.ProjectTwo.Specification.SpecificationEntity;
import com.example.ProjectTwo.dto.UserDto;
import com.example.ProjectTwo.entity.UserEntity;
import com.example.ProjectTwo.entity.UserEntity_;
import com.example.ProjectTwo.mapper.UserMapper;
import com.example.ProjectTwo.repository.UserRepository;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1")
public class UserController {
private final UserRepository userRepository;
private final UserMapper userMapper;

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
    @GetMapping("/filter/")
    public List<UserEntity> filter (@RequestBody UserDto userDto) {
        return userRepository.findAll(SpecificationEntity.ContainsValue(UserEntity_.name, userDto.getName())
                .and(SpecificationEntity.ContainsValue(UserEntity_.lastName, userDto.getLastName()))
                .and(SpecificationEntity.equalValue(UserEntity_.age, userDto.getAge())));
    }

}
