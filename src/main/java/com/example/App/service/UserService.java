package com.example.App.service;

import com.example.App.model.User;
import com.example.App.service.UserService;

import java.util.List;

import org.springframework.stereotype.Service;


@Service
public interface UserService {
User createUser(User user);
User getUserById(Integer id);
 List<User> getAllUsers();
 User updateUser(Integer id, User userDetails);
 void deleteUser(Integer id);
   
}

