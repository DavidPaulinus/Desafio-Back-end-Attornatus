package br.com.Attornatus.desafio.DTO.pessoa;

import java.time.LocalDate;
import java.util.List;

import br.com.Attornatus.desafio.model.Endereco;
import br.com.Attornatus.desafio.model.Pessoa;

public record PessoaDetalhamentoDTO(String nome, LocalDate dataNascimento, List<Endereco> endereco) {
	public PessoaDetalhamentoDTO(Pessoa pess) {
		this(pess.getNome(),pess.getDataNascimento(),pess.getEndereco());
	}
}
