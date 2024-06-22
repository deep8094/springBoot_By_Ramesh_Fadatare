package com.brar.springBoot_By_Ramesh_Fadatare.service;

import com.brar.springBoot_By_Ramesh_Fadatare.entity.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    User getUserById(Long id);
    List<User> getAllUsers();
    User updateUser(User user);
    User upsert(User user);
    void deleteUser(Long id);
}
