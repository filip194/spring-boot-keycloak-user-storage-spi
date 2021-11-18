package com.iot.demo.api.keycloak.remoteuserstoragespi.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

// actually, only getters and setters are needed, Keycloak takes care of constructors
@Getter
@Setter
//@ToString
//@NoArgsConstructor
//@AllArgsConstructor
public class User implements Serializable
{
    private static final long serialVersionUID = 5313493413859895497L;

    private String userId;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
}

