package br.com.Attornatus.desafio.DTO;

import jakarta.validation.constraints.NotBlank;

public record EnderecoDTO(
		@NotBlank String logradouro, 
		
		@NotBlank String cep,
		
		@NotBlank String numero,
		
		@NotBlank String cidade) {

}
