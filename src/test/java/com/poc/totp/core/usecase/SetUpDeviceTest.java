package com.poc.totp.core.usecase;

import com.poc.totp.core.dto.SetUpDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class SetUpDeviceTest {

	@Autowired
	SetUpDevice setUpDevice;

	@Test
	@DisplayName("Deve ser possível realizar o setup")
	void deveSerPossivelRealizarOSetup() {
		SetUpDTO dto = SetUpDTO.builder()
			.email(UUID.randomUUID().toString().concat("@email.com"))
			.nomeDispositivo(UUID.randomUUID().toString())
			.build();

		Assertions.assertDoesNotThrow(() -> setUpDevice.execute(dto));
	}

}
