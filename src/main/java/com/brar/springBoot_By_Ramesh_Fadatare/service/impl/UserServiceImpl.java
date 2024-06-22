package com.brar.springBoot_By_Ramesh_Fadatare.service.impl;

import com.brar.springBoot_By_Ramesh_Fadatare.dto.UserDto;
import com.brar.springBoot_By_Ramesh_Fadatare.entity.User;
import com.brar.springBoot_By_Ramesh_Fadatare.mapper.UserMapper;
import com.brar.springBoot_By_Ramesh_Fadatare.repository.UserRepository;
import com.brar.springBoot_By_Ramesh_Fadatare.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.brar.springBoot_By_Ramesh_Fadatare.mapper.UserMapper.*;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository; //no need to use @Autowire: injected by allArgsConstructor.

    @Override
    public UserDto createUser(UserDto userDto) {
        //convert userDTO to User Jpa entity
        User user = mapDTOtoEntityUser(userDto);
        //call the JPA Repository method
        User savedUser = userRepository.save(user);
        //convert User Jpa Entity to UserDTO
        return mapEntityToDTOUser(savedUser);
    }

    @Override
    public UserDto getUserById(Long id) {
        Optional<User> optionalUser =  userRepository.findById(id); //using optional to handle null response
        return mapEntityToDTOUser(optionalUser.get());
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(UserMapper::mapEntityToDTOUser).toList();
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User existingUser =  userRepository.findById(user.getId()).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        return mapEntityToDTOUser(userRepository.save(existingUser));
    }

    @Override
    public UserDto upsert(UserDto user) {
        Optional<User> existingUser = userRepository.findById(user.getId());
        if (existingUser.isPresent()) {
            existingUser.get().setFirstName(user.getFirstName());
            existingUser.get().setLastName(user.getLastName());
            existingUser.get().setEmail(user.getEmail());
            return mapEntityToDTOUser(userRepository.save(existingUser.get()));
        }
        else{
            User userObj = mapDTOtoEntityUser(user);
            User savedUser = userRepository.save(userObj);
            return mapEntityToDTOUser(savedUser); //since we are generating value of id at runtime, it will not pass.
        }
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
