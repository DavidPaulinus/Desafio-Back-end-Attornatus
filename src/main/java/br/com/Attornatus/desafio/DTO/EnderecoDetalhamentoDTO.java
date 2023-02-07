package br.com.Attornatus.desafio.DTO;

import br.com.Attornatus.desafio.model.Endereco;

public record EnderecoDetalhamentoDTO(String logradouro, String cep, String numero, String cidade) {
	public EnderecoDetalhamentoDTO(Endereco end) {
		this(end.getLogradouro(), end.getCep(), end.getNumero(),  end.getCidade());
	}
}
