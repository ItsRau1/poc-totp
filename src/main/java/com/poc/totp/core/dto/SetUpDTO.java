package com.poc.totp.core.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetUpDTO {

	@Email(message = "E-mail inválido.")
	@NotBlank(message = "E-mail obrigatório.")
	private String email;

	@NotBlank(message = "Nome do dispositivo obrigatório.")
	private String nomeDispositivo;

}
