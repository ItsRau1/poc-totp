package com.poc.totp.outbound.totp.gateway;

import com.poc.totp.core.domain.exception.setup.GerarQrCodeException;
import dev.samstevens.totp.code.HashingAlgorithm;
import dev.samstevens.totp.exceptions.QrGenerationException;
import dev.samstevens.totp.qr.QrData;
import dev.samstevens.totp.qr.QrDataFactory;
import dev.samstevens.totp.qr.QrGenerator;
import dev.samstevens.totp.qr.ZxingPngQrGenerator;
import dev.samstevens.totp.secret.DefaultSecretGenerator;
import dev.samstevens.totp.secret.SecretGenerator;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import static dev.samstevens.totp.util.Utils.getDataUriForImage;

@Component
@Log4j2
public class TotpGateway implements com.poc.totp.core.domain.gateway.TotpGateway {

	private final SecretGenerator secretGenerator = new DefaultSecretGenerator();

	private final QrDataFactory qrDataFactory = new QrDataFactory(HashingAlgorithm.SHA256, 6, 30);

	private final QrGenerator qrGenerator = new ZxingPngQrGenerator();

	@Override
	public String generateSecret() {
		log.info("Gerando secret.");
		return secretGenerator.generate();
	}

	@Override
	public String generateQrCode(String secret, String email, String label) {
		log.info("Iniciando geração de QrCode: [E: {}] [ND: {}]", email, label);
		QrData data = qrDataFactory.newBuilder().label(email).secret(secret).issuer(label).build();
		log.info("Gerando QrCode: [E: {}] [ND: {}]", email, label);
		byte[] qrCode = generateQrCode(data);
		log.info("Convertendo QrCode para Base64: [E: {}] [ND: {}]", email, label);
		return getDataUriForImage(qrCode, qrGenerator.getImageMimeType());
	}

	private byte[] generateQrCode(QrData data) {
		try {
			return qrGenerator.generate(data);
		}
		catch (QrGenerationException e) {
			throw new GerarQrCodeException();
		}
	}

}
