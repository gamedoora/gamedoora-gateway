# Gamedoora API Gateway

Welcome to the API Gateway for Gamedoora services. This application serves as the central hub for routing requests from the frontend to respective microservices. Equipped with filters, the gateway performs various checks and redirects requests to the appropriate services.

## Microservice Configuration

Our microservices utilize a configuration server to manage individual properties and define common properties shared among all microservices. For detailed information on how this works, please refer to [Spring Cloud Configuration](https://www.baeldung.com/spring-cloud-configuration).

To set up a microservice locally:

1. Clone the microservice repository. You can use this [demo resource server](https://github.com/arkaprovob/spring-boot-resource-server) or any other service from the Gamedoora [repositories](https://github.com/orgs/gamedoora/repositories).
2. Clone the [config server](https://github.com/gamedoora/gamedoora-config-server).
3. Create a local `application-local.yaml` file and add microservice-specific properties.
4. Run the microservice, pointing it to the config server for configuration.

## Gateway Configuration

Our gateway application is designed to run with Keycloak for Single Sign-On (SSO). To run the gateway locally, follow these steps:

1. Clone the gateway application locally.
2. Clone the [config server](https://github.com/gamedoora/gamedoora-config-server).
3. Set up a local database and add the details to the local `.properties` file.
4. Create a realm on Keycloak, make a client, and note down the necessary details (URL, username, client, etc.).
5. Generate a token for your Keycloak client. Refer to [Spring Boot Keycloak Integration](https://www.baeldung.com/spring-boot-keycloak) for more information.
6. Create a local `.properties` file and add Keycloak configurations.

## Testing the Application

To test the application locally:

1. Send a cURL request to the microservice application.
2. Include necessary details in the request header, such as username, password, and token.

If the configurations are correct, you should receive an appropriate response; otherwise, an error will occur.