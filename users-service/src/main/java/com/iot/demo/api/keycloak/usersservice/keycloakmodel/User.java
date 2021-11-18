package com.iot.demo.api.keycloak.usersservice.keycloakmodel;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Keycloak remote storage SPI User keycloakmodel
 * (class names do not have to be the same, but data fields have to be the same for Keycloak to successfully authenticate user)
 * NOTE: this is not classic user dto!
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable
{
    // this User keycloakmodel data must match fields data from remote User storage provider user keycloakmodel when returned
    // from this app's controller, because it will have to be mapped to Keycloak SPI User keycloakmodel so Keycloak can match it and use it
    // in authentication/authorization process

    private static final long serialVersionUID = 5313493413859894497L;

    private String userId;
    private String username;
    private String firstName;
    private String lastName;
    private String email;

}

