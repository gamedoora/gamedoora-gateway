package com.gamedoora.gateway;

import com.gamedoora.gateway.util.TestConfig;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@DirtiesContext #not needed as of now, if you have more than one test classes then uncomment it, avoids conflicts
@Import(TestConfig.class)
class GamedooraGatewayApplicationTests {

	//@Test
	void contextLoads() {
	}

	//@Test
	public void testGateway() {
		// Set up a test client for sending requests to the gateway
		WebTestClient client = WebTestClient.bindToServer().baseUrl("Base_Url").build();
		System.out.println("Check----------------------->");

		System.out.println(client.get().header("bearer"));
		System.out.println(client.get().header("id"));
		System.out.println(client.get().header("role"));


		// Send a test request to the gateway
		client.get()
				.uri("path/to/URL")
				.exchange()
				.expectStatus().isOk()
				.expectBody(String.class).isEqualTo("");
	}

}
