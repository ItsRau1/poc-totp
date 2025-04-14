package com.poc.totp.core.usecase;

import com.poc.totp.core.domain.gateway.TotpGateway;
import com.poc.totp.core.dto.SetUpDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class SetUpDevice {

	@Autowired
	TotpGateway totpGateway;

	public String execute(SetUpDTO dto) {
		log.info("Iniciando SetUp de dispositivo [DTO: {}]", dto);
		String secret = totpGateway.generateSecret();
		log.info("Secret gerado com sucesso.");
		return totpGateway.generateQrCode(secret, dto.getEmail(), dto.getNomeDispositivo());
	}

}
