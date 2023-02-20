package br.com.Attornatus.desafio.DTO.endereco;

import br.com.Attornatus.desafio.model.Endereco;

public record EnderecoListarDTO(String logradouro, String cep, String numero, String cidade) {
	public EnderecoListarDTO(Endereco end) {
		this(end.getLogradouro(), end.getCep(), end.getNumero(),  end.getCidade());
	}
}
