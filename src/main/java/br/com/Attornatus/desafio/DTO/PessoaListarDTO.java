package br.com.Attornatus.desafio.DTO;

import java.util.Date;
import java.util.List;

import br.com.Attornatus.desafio.model.Endereco;
import br.com.Attornatus.desafio.model.Pessoa;

public record PessoaListarDTO(String nome, Date dataNascimento, List<Endereco> endereco) {
	public PessoaListarDTO(Pessoa pess) {
		this(pess.getNome(), pess.getDataNascimento(), pess.getEndereco());
	}
}
