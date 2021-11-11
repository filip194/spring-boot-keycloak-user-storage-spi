# Spring Boot Keycloak User Storage SPI
Spring Boot User Web Service hosting Postgres DB, and custom Keycloak User Storage SPI (connecting to remote Postgres DB instead of default H2)

There are two independent projects in this repository:

## Remote User Storage Provider (remote-user-storage-provider)

This application should be maven packaged and deployed to Keycloak Server to act as custom usr storage SPI, so Keycloak uses other DB instead of inbuilt H2. In this case we are going to use Postgres DB as remote database. 

*NOTE:* even though we use Postgres DB, H2 will also be used. Keycloak will first look up for user in H2 database, if it is not found, then it will look into Postgres DB. 

## Users Service (users-service)

Standalone Spring Boot application that will provide user service and connection to Postgres DB, to remote-user-storage-provider application that is deployed on Keycloak server.
