# Gamedoora API Gateway

Welcome to the API Gateway for Gamedoora services. Backed by an OAuth2 authorization server, this application authenticates all incoming requests before they access the resources from the backend servers.

This application is primarily designed to:

1. Function as an API gateway, offering a uniform interface to the underlying resource servers.
2. Decouple the authentication and authorization concerns from the resource servers, promoting a more modular architecture

## Setup Instructions

### Prerequisites

1. Clone the repository.
2. Have an instance of Keycloak or any OAuth2-supported identity and access management application configured up and running.
3. (Optional) Clone the [config server](https://github.com/gamedoora/gamedoora-config-server).
4. Clone, set up, and run a resource server for testing purposes. You can use this [demo resource server](https://github.com/arkaprovob/spring-boot-resource-server) or any other service from the Gamedoora [repositories](https://github.com/orgs/gamedoora/repositories).

This application can fetch configurations in two ways:

1. From a local properties file.
2. From a Configuration server.

### Configuring with the Local Properties File

1. Create a `application-local.yaml` file under the resource folder.
2. Refer to [application-config-sample.yaml](src%2Fmain%2Fresources%2Fapplication-config-sample.yaml) and adjust the values of your newly created properties file accordingly.
3. Set the active profile as `local` and start the application.

### Configuring with the Spring Boot Configuration Server

1. Clone, configure, and start the [config server](https://github.com/gamedoora/gamedoora-config-server) for the API gateway application.
2. Set the following two environment variables and start the server:
    1. `CONFIG_SERVER` - Set this to the URL of your config server (append a `/` at the end).
    2. `PROFILE` - This should match the profile you're using and which is available in the config server, registered for your application.

After these steps, start the API gateway. Test the configuration by attempting to access a newly configured route. For example, if you used the [Demo resource server](https://github.com/arkaprovob/spring-boot-resource-server) and kept the route information as given in [application-config-sample.yaml](src%2Fmain%2Fresources%2Fapplication-config-sample.yaml), try accessing this URL: `http://localhost:8181/doora/r1/resource`.

If the configurations are correct, you will be redirected to the OAuth server's login page. Post authentication, you should be able to see the content from the pre-configured demo resource server.