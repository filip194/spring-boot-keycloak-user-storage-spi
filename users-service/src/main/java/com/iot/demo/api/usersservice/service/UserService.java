package com.iot.demo.api.usersservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.iot.demo.api.usersservice.dao.entity.UserEntity;
import com.iot.demo.api.usersservice.dao.repository.UserRepository;
import com.iot.demo.api.usersservice.model.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService
{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder)
    {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUserDetails(String userName)
    {
        final User user = new User();

        final UserEntity userEntity = userRepository.findByEmail(userName);
        if (userEntity == null)
        {
            return user;
        }

        BeanUtils.copyProperties(userEntity, user);

        return user;
    }

    public User getUserDetails(String userName, String password)
    {
        User user = null;

        final UserEntity userEntity = userRepository.findByEmail(userName);

        if (userEntity == null)
        {
            return user;
        }

        if (passwordEncoder.matches(password, userEntity.getPassword()))
        {
            log.info("Password is a match!");

            user = new User();
            BeanUtils.copyProperties(userEntity, user);
        }

        return user;
    }

    public List<User> getAllUsers()
    {
        return userRepository.findAll().stream().map(this::convertUserEntityToUserModel).collect(Collectors.toList());
    }

    private User convertUserEntityToUserModel(final UserEntity entity)
    {
        final User user = new User();
        user.setUsername(entity.getUsername());
        user.setType(entity.getType());
        user.setFirstName(entity.getFirstName());
        user.setLastName(entity.getLastName());
        user.setEmail(entity.getEmail());
        user.setAge(entity.getAge());
        return user;
    }
}

