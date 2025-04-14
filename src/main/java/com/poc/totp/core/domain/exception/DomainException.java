package com.poc.totp.core.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class DomainException extends RuntimeException {

	public HttpStatus status;

	public DomainException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}

}
