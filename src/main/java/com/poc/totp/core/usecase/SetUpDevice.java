package com.poc.totp.core.usecase;

import com.poc.totp.core.domain.gateway.TotpGateway;
import com.poc.totp.core.dto.SetUpDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SetUpDevice {

	@Autowired
	TotpGateway totpGateway;

	public String execute(SetUpDTO dto) {
		return totpGateway.generateQrCode(dto.getEmail(), dto.getNomeDispositivo());
	}

}
