package br.com.Attornatus.desafio.DTO;

import br.com.Attornatus.desafio.model.Endereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PessoaDTO(
		@NotBlank
		String nome, 
		
		@NotNull
		String dataNascimento, 
		
		@NotNull
		@Valid
		Endereco endereco) {

}
