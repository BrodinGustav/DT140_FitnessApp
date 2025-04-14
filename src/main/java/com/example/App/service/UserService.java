package com.example.App.service;

import com.example.App.dto.UpdateUserDTO;
import com.example.App.model.User;
import com.example.App.service.UserService;

import java.util.List;

public interface UserService {
    User createUser(User user);

    User getUserById(Integer id);

    List<User> getAllUsers();

    User updateUser(Integer id, UpdateUserDTO userDetails);

    void deleteUser(Integer id);

}
