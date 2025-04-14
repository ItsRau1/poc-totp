package com.poc.totp.inbound;

import com.poc.totp.core.dto.SetUpDTO;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TotpControllerTest {

	@LocalServerPort
	private int port;

	@BeforeEach
	public void setUp() {
		RestAssured.port = port;
	}

	@Test
	@DisplayName("Deve ser possível realizar o setup")
	void deveSerPossivelRealizarOSetup() {
		SetUpDTO dto = SetUpDTO.builder()
			.email(UUID.randomUUID().toString().concat("@email.com"))
			.nomeDispositivo(UUID.randomUUID().toString())
			.build();

		RestAssured.given()
			.log()
			.all()
			.contentType("application/json")
			.body(dto)
			.when()
			.post("/poc/totp/v1/setup")
			.then()
			.log()
			.all()
			.statusCode(HttpStatus.OK.value());
	}

}
