package com.brar.springBoot_By_Ramesh_Fadatare.service.impl;

import com.brar.springBoot_By_Ramesh_Fadatare.entity.User;
import com.brar.springBoot_By_Ramesh_Fadatare.repository.UserRepository;
import com.brar.springBoot_By_Ramesh_Fadatare.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository; //no need to use @Autowire: injected by allArgsConstructor.

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> optionalUser =  userRepository.findById(id); //using optional to handle null response
        return optionalUser.get();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user) {
        User existingUser =  userRepository.findById(user.getId()).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        return userRepository.save(existingUser);
    }

    @Override
    public User upsert(User user) {
        Optional<User> existingUser = userRepository.findById(user.getId());
        if (existingUser.isPresent()) {
            existingUser.get().setFirstName(user.getFirstName());
            existingUser.get().setLastName(user.getLastName());
            existingUser.get().setEmail(user.getEmail());
            return userRepository.save(existingUser.get());
        }
        else{
            return userRepository.save(user); //since we are generating value of id at runtime, it will not pass.
        }
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
