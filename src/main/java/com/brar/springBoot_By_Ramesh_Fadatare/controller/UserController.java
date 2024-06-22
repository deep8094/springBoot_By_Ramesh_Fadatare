package com.brar.springBoot_By_Ramesh_Fadatare.controller;
import com.brar.springBoot_By_Ramesh_Fadatare.dto.UserDto;
import com.brar.springBoot_By_Ramesh_Fadatare.entity.User;
import com.brar.springBoot_By_Ramesh_Fadatare.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) {
        UserDto savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto getUser = userService.getUserById(id);
        return new ResponseEntity<>(getUser, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto user) {
        user.setId(id);
        UserDto savedUser = userService.updateUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    @PutMapping("upsert/{id}")
    public ResponseEntity<UserDto> upsert(@PathVariable Long id, @RequestBody UserDto user) {
        user.setId(id);
        UserDto savedUser = userService.upsert(user);
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>("User successfully deleted",HttpStatus.OK);
    }
}
