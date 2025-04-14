package com.poc.totp.core.domain.gateway;

public interface TotpGateway {

	String generateQrCode(String email, String label);

}
