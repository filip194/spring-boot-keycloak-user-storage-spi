package com.iot.demo.api.usersservice.controller;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iot.demo.api.usersservice.model.User;
import com.iot.demo.api.usersservice.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController
{
    private final UserService userService;

    public UserController(UserService usersService)
    {
        this.userService = usersService;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<User>> getUsers()
    {
        log.info("Getting all users");
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping(path = "/{username}")
    public ResponseEntity<User> getUserByUsername(@PathParam(value = "username") String username)
    {
        log.info("Getting user by username: {}", username);
        final Optional<User> user = userService.getUserByUsername(username);
        return user.map(userValue -> new ResponseEntity<>(userValue, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}

