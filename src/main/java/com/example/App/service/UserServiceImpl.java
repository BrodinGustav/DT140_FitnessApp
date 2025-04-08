package com.example.App.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.App.execption.ResourceNotFoundException;
import com.example.App.model.User;
import com.example.App.repository.UserRepository;

public class UserServiceImpl implements UserService {

       @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Användare med id " + id + " hittades inte."));
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new ResourceNotFoundException("Inga användare hittades.");
        }
        return users;
    }

    @Override
    public User updateUser(Integer id, User userDetails) {
        User user = userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Användare med id " + id + " hittades inte."));
        
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Integer id) {
        User user = userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Användare med id " + id + " finns inte."));
    userRepository.delete(user);
    }

    
}
