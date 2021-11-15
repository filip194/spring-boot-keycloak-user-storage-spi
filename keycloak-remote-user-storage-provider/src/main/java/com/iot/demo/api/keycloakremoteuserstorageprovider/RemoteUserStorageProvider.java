package com.iot.demo.api.keycloakremoteuserstorageprovider;

import java.util.stream.Collectors;

import org.keycloak.component.ComponentModel;
import org.keycloak.credential.CredentialInput;
import org.keycloak.credential.CredentialInputValidator;
import org.keycloak.credential.UserCredentialStore;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.models.credential.PasswordCredentialModel;
import org.keycloak.storage.StorageId;
import org.keycloak.storage.UserStorageProvider;
import org.keycloak.storage.adapter.AbstractUserAdapter;
import org.keycloak.storage.user.UserLookupProvider;

import lombok.extern.slf4j.Slf4j;

@Slf4j
// these are minimal 3 interfaces to implement to let keycloak get user credentials from remote storage and then validate user credentials
public class RemoteUserStorageProvider implements UserStorageProvider, UserLookupProvider, CredentialInputValidator
{
    private final KeycloakSession session;
    private final ComponentModel componentModel;
    private final UsersAPIService usersAPIService;

    public RemoteUserStorageProvider(KeycloakSession session, ComponentModel componentModel,
            UsersAPIService usersAPIService)
    {
        this.session = session;
        this.componentModel = componentModel;
        this.usersAPIService = usersAPIService;
    }

    // ###################
    // UserStorageProvider
    // ###################

    @Override
    public void close()
    {
    }

    // ############################################################
    // UserLookupProvider - lookup user details from remote storage
    // ############################################################

    @Override
    public UserModel getUserById(String id, RealmModel realm)
    {
        final StorageId storageId = new StorageId(id);
        final String username = storageId.getExternalId();
        log.info("getUserById() ::: id: {}", id);
        log.info("getUserById() ::: username(externalId): {}", username);
        log.info("getUserById() ::: providerId: {}", storageId.getProviderId());
        return getUserByUsername(username, realm);
    }

    @Override
    public UserModel getUserByUsername(String username, RealmModel realm)
    {
        log.info("getUserByUsername() ::: username: {}", username);
        UserModel userModel = null;
        final User user = usersAPIService.getUserDetails(username);

        if (user != null)
        {
            userModel = createUserModel(username, realm);
        }

        return userModel;
    }

    @Override
    public UserModel getUserByEmail(String email, RealmModel realm)
    {
        return null;
    }

    private UserModel createUserModel(final String username, RealmModel realm)
    {
        log.info("createUserModel() USERNAME: {}", username);

        return new AbstractUserAdapter(session, realm, componentModel)
        {
            @Override
            public String getUsername()
            {
                return username;
            }
        };
    }

    // ####################################################
    // CredentialInputValidator - validate user credentials
    // ####################################################

    // checks if credential provider supports given credential type, which is in our case password
    @Override
    public boolean supportsCredentialType(String credentialType)
    {
        log.info("supportsCredentialType() ::: credentialType: {}", credentialType);
        return PasswordCredentialModel.TYPE.equals(credentialType);
    }

    // checks if credential type is configured for given user
    @Override
    public boolean isConfiguredFor(RealmModel realm, UserModel user, String credentialType)
    {
        if (!supportsCredentialType(credentialType))
        {
            return false;
        }
        return !getUserCredentialStore().getStoredCredentialsByTypeStream(realm, user, credentialType)
                .collect(Collectors.toList()).isEmpty();
    }

    private UserCredentialStore getUserCredentialStore()
    {
        return session.userCredentialManager();
    }

    // invoked by Keycloak automatically when it needs to validate user password
    @Override
    public boolean isValid(RealmModel realm, UserModel user, CredentialInput credentialInput)
    {
        log.info("isValid() EMAIL: {}", user.getEmail());
        log.info("isValid() USERNAME: {}", user.getUsername());
        log.info("isValid() PASSWORD: {}", credentialInput.getChallengeResponse());

        final VerifyPasswordResponse verifyPasswordResponse = usersAPIService.verifyUserPassword(user.getUsername(),
                credentialInput.getChallengeResponse());

        if (verifyPasswordResponse == null)
        {
            return false;
        }
        return verifyPasswordResponse.isVerified();
    }

}

