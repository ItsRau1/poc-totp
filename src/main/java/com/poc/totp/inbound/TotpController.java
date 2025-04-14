package com.poc.totp.inbound;

import com.poc.totp.core.dto.SetUpDTO;
import com.poc.totp.core.usecase.SetUpDevice;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("poc/totp/v1")
public class TotpController {

	@Autowired
	SetUpDevice setUpDevice;

	@PostMapping("/setup")
	ResponseEntity<String> setup(@RequestBody @Valid SetUpDTO setUpDTO) {
		return ResponseEntity.ok(setUpDevice.execute(setUpDTO));
	}

}
