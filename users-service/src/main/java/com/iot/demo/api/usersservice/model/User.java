package com.iot.demo.api.usersservice.model;

import java.io.Serializable;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.iot.demo.api.usersservice.dao.entity.UserType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable
{
    private static final long serialVersionUID = 5313493413859894497L;

    private UUID userId;
    private UserType type;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Integer age;

}

