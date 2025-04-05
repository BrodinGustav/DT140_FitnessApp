package com.example.App.service;

import com.example.App.execption.ResourceNotFoundException;
import com.example.App.model.User;
import com.example.App.repository.UserRepository;
import com.example.App.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserService {

      @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Användare med id " + id + " hittades inte."));
    }

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new ResourceNotFoundException("Inga användare hittades.");
        }
        return users;
    }

    public User updateUser(Integer id, User userDetails) {
        User user = userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Användare med id " + id + " hittades inte."));
        
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        return userRepository.save(user);
    }

    public void deleteUser(Integer id) {
        User user = userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Användare med id " + id + " finns inte."));
    userRepository.delete(user);
    }

}

