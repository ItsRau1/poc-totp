package com.poc.totp.core.domain.gateway;

public interface TotpGateway {

	String generateSecret();

	String generateQrCode(String secret, String email, String label);

}
