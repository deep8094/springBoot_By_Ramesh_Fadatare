package com.brar.springBoot_By_Ramesh_Fadatare.mapper;

import com.brar.springBoot_By_Ramesh_Fadatare.dto.UserDto;
import com.brar.springBoot_By_Ramesh_Fadatare.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {
    public static UserDto mapEntityToDTOUser(User savedUser) {
        return new UserDto(savedUser.getId(),savedUser.getFirstName(),savedUser.getLastName(),savedUser.getEmail());
    }

    public static User mapDTOtoEntityUser(UserDto userDto) {
        return new User(userDto.getId(),userDto.getFirstName(),userDto.getLastName(),userDto.getEmail());
    }
}
