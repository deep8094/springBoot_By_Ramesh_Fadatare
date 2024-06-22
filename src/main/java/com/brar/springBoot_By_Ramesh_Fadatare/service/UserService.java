package com.brar.springBoot_By_Ramesh_Fadatare.service;

import com.brar.springBoot_By_Ramesh_Fadatare.dto.UserDto;
import com.brar.springBoot_By_Ramesh_Fadatare.entity.User;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto user);
    UserDto getUserById(Long id);
    List<UserDto> getAllUsers();
    UserDto updateUser(UserDto user);
    UserDto upsert(UserDto user);
    void deleteUser(Long id);
}
