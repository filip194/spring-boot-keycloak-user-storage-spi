package com.iot.demo.api.remoteuserstorageprovider;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.iot.demo.api.remoteuserstorageprovider.model.User;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
public interface UsersAPIService
{
    @GET
    @Path("/{username}")
    User getUserDetails(@PathParam("username") String name);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{username}/verify-password")
    VerifyPasswordResponse verifyUserPassword(@PathParam("username") String username, String password);

}