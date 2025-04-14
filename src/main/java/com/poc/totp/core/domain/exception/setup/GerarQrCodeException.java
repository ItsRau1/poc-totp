package com.poc.totp.core.domain.exception.setup;

import com.poc.totp.core.domain.exception.DomainException;
import org.springframework.http.HttpStatus;

public class GerarQrCodeException extends DomainException {

	public GerarQrCodeException() {
		super("Erro ao gerar QrCode.", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
