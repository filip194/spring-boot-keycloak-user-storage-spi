package com.iot.demo.api.keycloak.usersservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.iot.demo.api.keycloak.usersservice.dao.entity.UserEntity;
import com.iot.demo.api.keycloak.usersservice.dao.repository.UserRepository;
import com.iot.demo.api.keycloak.usersservice.dto.UserDto;
import com.iot.demo.api.keycloak.usersservice.keycloakmodel.User;

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

    public List<UserDto> getAllUsers()
    {
        return userRepository.findAll().stream().map(this::convertUserEntityToUserDto).collect(Collectors.toList());
    }

    private UserDto convertUserEntityToUserDto(final UserEntity entity)
    {
        final UserDto userDto = new UserDto();
        userDto.setUserId(entity.getUserId());
        userDto.setUsername(entity.getUsername());
        userDto.setType(entity.getType());
        userDto.setFirstName(entity.getFirstName());
        userDto.setLastName(entity.getLastName());
        userDto.setEmail(entity.getEmail());
        userDto.setEmailVerified(entity.getEmailVerified());
        userDto.setAge(entity.getAge());
        userDto.setAccessToken(entity.getAccessToken());
        userDto.setRefreshToken(entity.getRefreshToken());
        return userDto;
    }
}

