package com.poc.totp.outbound.totp.gateway;

import dev.samstevens.totp.code.HashingAlgorithm;
import dev.samstevens.totp.exceptions.QrGenerationException;
import dev.samstevens.totp.qr.QrData;
import dev.samstevens.totp.qr.QrDataFactory;
import dev.samstevens.totp.qr.QrGenerator;
import dev.samstevens.totp.qr.ZxingPngQrGenerator;
import dev.samstevens.totp.secret.DefaultSecretGenerator;
import dev.samstevens.totp.secret.SecretGenerator;
import org.springframework.stereotype.Component;

import static dev.samstevens.totp.util.Utils.getDataUriForImage;

@Component
public class TotpGateway implements com.poc.totp.core.domain.gateway.TotpGateway {

	private final SecretGenerator secretGenerator = new DefaultSecretGenerator();

	private final QrDataFactory qrDataFactory = new QrDataFactory(HashingAlgorithm.SHA256, 6, 30);

	private final QrGenerator qrGenerator = new ZxingPngQrGenerator();

	@Override
	public String generateQrCode(String email, String label) {
		String secret = secretGenerator.generate();
		QrData data = qrDataFactory.newBuilder().label(email).secret(secret).issuer(label).build();
		try {
			return getDataUriForImage(qrGenerator.generate(data), qrGenerator.getImageMimeType());
		}
		catch (QrGenerationException e) {
			throw new RuntimeException(e);
		}
	}

}
