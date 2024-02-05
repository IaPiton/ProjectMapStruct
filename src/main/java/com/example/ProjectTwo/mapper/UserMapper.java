package com.example.ProjectTwo.mapper;

import com.example.ProjectTwo.dto.UserDto;
import com.example.ProjectTwo.entity.UserEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import java.util.List;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface UserMapper {
    UserEntity userDtoToUserEntity(UserDto userDto);
    UserDto userEntityToUserDto(UserEntity userEntity);

    List<UserDto> userEntitysToUserDtos(List<UserEntity> userEntities );
}
