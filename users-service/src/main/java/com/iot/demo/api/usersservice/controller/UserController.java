package com.iot.demo.api.usersservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iot.demo.api.usersservice.dto.UserDto;
import com.iot.demo.api.usersservice.keycloakmodel.User;
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
    public ResponseEntity<List<UserDto>> getUsers()
    {
        log.info("Getting all users");
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    // ==================================================================================================
    // these endpoints must be defined EXACTLY LIKE THIS for keycloak remote user storage to trigger them
    // and find user details in database
    // ==================================================================================================

    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String userName)
    {
        return userService.getUserDetails(userName);
    }

    @PostMapping("/{username}/verify-password")
    public VerifyPasswordResponse verifyUserPassword(@PathVariable("username") String userName,
            @RequestBody String password)
    {
        final VerifyPasswordResponse passwordVerificationStatus = new VerifyPasswordResponse(false);
        final User user = userService.getUserDetails(userName, password);

        if (user != null)
        {
            passwordVerificationStatus.setVerified(true);
        }

        return passwordVerificationStatus;
    }
}

