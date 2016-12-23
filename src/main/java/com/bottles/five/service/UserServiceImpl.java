package com.bottles.five.service;

import com.bottles.five.dao.UserRepository;
import com.bottles.five.model.User;
import com.bottles.five.rest.model.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto signIn(String login, String password) {
        User user = userRepository.findByLoginAndPassword(login, password);
        if (user == null)
            return null;

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    @Override
    public Long signUp(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        user = userRepository.save(user);
        userRepository.flush();
        return user.getUserId();
    }

    @Override
    public Boolean update(UserDto userDto) {
        User user = userRepository.findOne(userDto.getUserId());
        if (user == null)
            return false;

        BeanUtils.copyProperties(userDto, user);
        userRepository.save(user);
        return true;
    }

    @Override
    public Boolean exists(String login, String email) {
        User user = userRepository.findByLoginOrEmail(login, email);
        return user != null;
    }
}
