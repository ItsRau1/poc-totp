package com.poc.totp.inbound.config;

import com.poc.totp.core.domain.exception.DomainException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(DomainException.class)
	public ResponseEntity<ApiError> handleDomainException(WebRequest request, DomainException exception) {
		return ResponseEntity.status(exception.getStatus()).body(new ApiError(List.of(exception.getMessage())));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiError> handleInvalidArgument(MethodArgumentNotValidException exception) {
		List<String> errors = exception.getBindingResult()
			.getFieldErrors()
			.stream()
			.map(DefaultMessageSourceResolvable::getDefaultMessage)
			.toList();

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiError(errors));
	}

}
