package br.com.Attornatus.desafio.DTO.pessoa;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.Attornatus.desafio.model.Endereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PessoaDTO(
		@NotBlank
		String nome, 
		
		@NotNull
		@JsonFormat(pattern = "dd/MM/yyyy")
		LocalDate dataNascimento, 
		
		@NotNull
		@Valid
		Endereco endereco) {

}
