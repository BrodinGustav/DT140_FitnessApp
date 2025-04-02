package com.example.App.service;

import com.example.App.model.User;
import com.example.App.repository.UserRepository;
import com.example.App.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class UserService {

      @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Användare hittades inte"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}

