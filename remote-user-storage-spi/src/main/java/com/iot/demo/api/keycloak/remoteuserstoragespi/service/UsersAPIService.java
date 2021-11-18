package com.iot.demo.api.keycloak.remoteuserstoragespi.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.iot.demo.api.keycloak.remoteuserstoragespi.model.User;
import com.iot.demo.api.keycloak.remoteuserstoragespi.provider.VerifyPasswordResponse;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
public interface UsersAPIService
{
    // =========================================================================================
    // these are definitions for requests that need to be sent to remote Spring Boot Web Service
    // to get user details and to validate user password on remote database
    // =========================================================================================

    @GET
    @Path("/{username}")
    User getUserDetails(@PathParam("username") String name);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{username}/verify-password")
        // username and password which user will use to log in to Keycloak
    VerifyPasswordResponse verifyUserPassword(@PathParam("username") String username, String password);

}
