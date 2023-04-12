package com.hrs.user.service.service;

import com.hrs.user.service.entities.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    List<User> getAllUsers();

    User getUser(String userId);

    User updateUser(String userId);

    void deleteUser(String userId);
}
