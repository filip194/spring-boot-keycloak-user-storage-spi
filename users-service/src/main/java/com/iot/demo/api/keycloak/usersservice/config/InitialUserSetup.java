package com.iot.demo.api.keycloak.usersservice.config;

/**
 * Only an example on how data can be added to database on application startup
 */
@Deprecated(since = "...Flyway was introduced. initUser() method will add the same new user record into DB each time application is started.")
//@Component
public class InitialUserSetup
{
    //    private final UserRepository userRepository;
    //    private final PasswordEncoder passwordEncoder;
    //
    //    public InitialUserSetup(UserRepository userRepository, PasswordEncoder passwordEncoder)
    //    {
    //        this.userRepository = userRepository;
    //        this.passwordEncoder = passwordEncoder;
    //    }
    //
    //    @EventListener
    //    @Transactional
    //    public void initUser(ApplicationReadyEvent event)
    //    {
    //        final UserEntity entity = new UserEntity(
    //                100,
    //                UUID.randomUUID(),
    //                UserType.USER,
    //                "test_user",
    //                passwordEncoder.encode("123"),
    //                "test@mail.com",
    //                false,
    //                "first_name",
    //                "last_name",
    //                25,
    //                "no-token",
    //                "no-refresh-token",
    //                new Timestamp(System.currentTimeMillis()),
    //                new Timestamp(System.currentTimeMillis())
    //        );
    //        userRepository.save(entity);
    //    }
}

