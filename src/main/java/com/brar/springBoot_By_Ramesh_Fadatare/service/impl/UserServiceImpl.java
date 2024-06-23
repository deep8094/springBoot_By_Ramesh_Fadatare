package com.brar.springBoot_By_Ramesh_Fadatare.service.impl;

import com.brar.springBoot_By_Ramesh_Fadatare.dto.UserDto;
import com.brar.springBoot_By_Ramesh_Fadatare.entity.User;
import com.brar.springBoot_By_Ramesh_Fadatare.mapper.UserMapper;
import com.brar.springBoot_By_Ramesh_Fadatare.repository.UserRepository;
import com.brar.springBoot_By_Ramesh_Fadatare.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.brar.springBoot_By_Ramesh_Fadatare.mapper.UserMapper.*;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private ModelMapper modelMapper;
    private UserRepository userRepository; //no need to use @Autowire: injected by allArgsConstructor.

    @Override
    public UserDto createUser(UserDto userDto) {
        //User user = mapDTOtoEntityUser(userDto);
        User user = modelMapper.map(userDto, User.class);

        User savedUser = userRepository.save(user);

        //return mapEntityToDTOUser(savedUser);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto getUserById(Long id) {
        Optional<User> optionalUser =  userRepository.findById(id); //using optional to handle null response
        //return mapEntityToDTOUser(optionalUser.get());
        return modelMapper.map(optionalUser.get(), UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        //return userRepository.findAll().stream().map(UserMapper::mapEntityToDTOUser).toList();
        return userRepository.findAll().stream().map(user->modelMapper.map(user,UserDto.class)).toList();
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User existingUser =  userRepository.findById(user.getId()).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        //return mapEntityToDTOUser(userRepository.save(existingUser));
        return modelMapper.map(existingUser, UserDto.class);
    }

    @Override
    public UserDto upsert(UserDto user) {
        Optional<User> existingUser = userRepository.findById(user.getId());
        if (existingUser.isPresent()) {
            existingUser.get().setFirstName(user.getFirstName());
            existingUser.get().setLastName(user.getLastName());
            existingUser.get().setEmail(user.getEmail());
            //return mapEntityToDTOUser(userRepository.save(existingUser.get()));
            return modelMapper.map(existingUser.get(), UserDto.class);
        }
        else{
            User userObj = mapDTOtoEntityUser(user);
            User savedUser = userRepository.save(userObj);
            //return mapEntityToDTOUser(savedUser); //since we are generating value of id at runtime, it will not pass.
            return modelMapper.map(savedUser, UserDto.class);
        }
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
