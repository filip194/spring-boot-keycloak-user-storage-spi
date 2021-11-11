package com.iot.demo.api.usersservice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.iot.demo.api.usersservice.dao.entity.UserEntity;
import com.iot.demo.api.usersservice.dao.repository.UserRepository;
import com.iot.demo.api.usersservice.model.User;

@Service
public class UserService
{
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers()
    {
        return userRepository.findAll().stream().map(this::convertUserEntityToUserModel).collect(Collectors.toList());
    }

    public Optional<User> getUserByUsername(final String username)
    {
        return userRepository.findByUsername(username).map(this::convertUserEntityToUserModel);
    }

    private User convertUserEntityToUserModel(final UserEntity entity)
    {
        final User user = new User();
        user.setUserId(entity.getUserId());
        user.setUsername(entity.getUsername());
        user.setType(entity.getType());
        user.setFirstName(entity.getFirstName());
        user.setLastName(entity.getLastName());
        user.setEmail(entity.getEmail());
        user.setAge(entity.getAge());
        return user;
    }
}

