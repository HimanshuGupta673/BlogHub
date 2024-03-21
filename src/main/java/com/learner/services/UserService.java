package com.learner.services;

import com.learner.payLoad.UserDto;

import java.util.List;

public interface UserService {
    UserDto create(UserDto user);
    UserDto updateUser(UserDto user,Integer userId);

    UserDto getUserById(Integer userId);

    List<UserDto> getAllUsers();

    void deleteUser(Integer userId);

    UserDto registerNewUser(UserDto userDto);
}
