package com.iot.demo.api.usersservice.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.iot.demo.api.usersservice.dao.entity.UserType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto
{
    private UUID userId;
    private UserType type;
    private String username;
    private String email;
    private Boolean emailVerified;
    private String firstName;
    private String lastName;
    private Integer age;
    private String accessToken;
    private String refreshToken;

}

