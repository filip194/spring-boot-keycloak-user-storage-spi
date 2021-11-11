package com.iot.demo.api.usersservice.config;

import java.sql.Timestamp;
import java.util.UUID;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.iot.demo.api.usersservice.dao.entity.UserEntity;
import com.iot.demo.api.usersservice.dao.entity.UserType;
import com.iot.demo.api.usersservice.dao.repository.UserRepository;

@Component
public class InitialUserSetup
{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public InitialUserSetup(UserRepository userRepository, PasswordEncoder passwordEncoder)
    {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @EventListener
    @Transactional
    public void initUser(ApplicationReadyEvent event)
    {
        final UserEntity entity = new UserEntity(
                100,
                UUID.randomUUID(),
                UserType.USER,
                "test_user",
                passwordEncoder.encode("123"),
                "test@mail.com",
                false,
                "first_name",
                "last_name",
                25,
                "no-token",
                "no-refresh-token",
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())
        );
        userRepository.save(entity);
    }
}

