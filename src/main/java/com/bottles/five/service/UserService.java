package com.bottles.five.service;

import com.bottles.five.rest.model.UserDto;

public interface UserService {
    UserDto signIn(String login, String password);
    Long signUp(UserDto userDto);
    Boolean update(UserDto userDto);
    Boolean exists(String login, String email);
}
