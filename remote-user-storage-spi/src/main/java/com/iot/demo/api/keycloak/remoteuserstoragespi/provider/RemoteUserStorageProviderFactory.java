package com.iot.demo.api.keycloak.remoteuserstoragespi.provider;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.storage.UserStorageProviderFactory;

import com.iot.demo.api.keycloak.remoteuserstoragespi.service.UsersAPIService;

public class RemoteUserStorageProviderFactory implements UserStorageProviderFactory<RemoteUserStorageProvider>
{
    // is also used as .jar name
    public static final String PROVIDER_NAME = "custom-user-storage-provider";
    public static final String LOCALHOST_URI = "http://localhost:9099";

    // this is where we are going to create new instance of UserStorageProvider interface/class
    @Override
    public RemoteUserStorageProvider create(KeycloakSession session, ComponentModel model)
    {
        return new RemoteUserStorageProvider(session, model, buildHttpClient(LOCALHOST_URI));
    }

    // identifies the factory in the runtime, it will return String value that will be shown in keycloak admin console
    // under User Federation when we want to enable UserStorageProvider for the realm
    @Override
    public String getId()
    {
        return PROVIDER_NAME;
    }

    // building adn implementing HTTP client from UsersAPIService interface with target URI of remote Spring Boot application
    // that will be used to access user data
    private UsersAPIService buildHttpClient(String uri)
    {
        final ResteasyClient resteasyClient = new ResteasyClientBuilder().build();
        final ResteasyWebTarget resteasyWebTarget = resteasyClient.target(uri);

        return resteasyWebTarget
                .proxyBuilder(UsersAPIService.class)
                .classloader(UsersAPIService.class.getClassLoader())
                .build();
    }

}

