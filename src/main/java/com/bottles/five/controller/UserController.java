package com.bottles.five.controller;

import com.bottles.five.rest.model.UserDto;
import com.bottles.five.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public ResponseEntity<UserDto> signIn(@RequestParam("login") String login,
                                          @RequestParam("password") String password) {
        LOGGER.info("signIn POST request");

        LOGGER.info("User login: " + login);
        LOGGER.info("User password: " + password);

        UserDto userDto = userService.signIn(login, password);
        if (userDto == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public ResponseEntity<Long> signUp(@RequestBody UserDto newUserDto) {
        LOGGER.info("signUp POST request");

        LOGGER.info("New user: " + newUserDto);

        boolean exists = userService.exists(newUserDto.getLogin(), newUserDto.getEmail());
        if (exists)
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        Long userId = userService.signUp(newUserDto);
        LOGGER.info("New user id: " + userId);

        return new ResponseEntity<>(userId, HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Boolean> update(@RequestBody UserDto userDto) {
        LOGGER.info("update POST request");

        LOGGER.info("User tp update: " + userDto);

        Boolean updated = userService.update(userDto);
        if (!updated)
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
