package br.com.Attornatus.desafio.service;

import java.text.ParseException;
import java.util.List;

import br.com.Attornatus.desafio.DTO.PessoaDTO;
import br.com.Attornatus.desafio.model.Endereco;
import jakarta.validation.Valid;

public class Acao {
	public void adicionarEndereco(List<Endereco> endereco, Endereco end) throws ParseException {
		endereco.add(naoFavoritoEndereco(endereco, end));
	}

	public void atualizarEndereco(List<Endereco> endereco, PessoaDTO dto) throws ParseException {
		for (Endereco end : endereco) {
			if (end.getId() == dto.endereco().getId()) {

				end.setCep(dto.endereco().getCep());
				end.setCidade(dto.endereco().getCidade());
				end.setLogradouro(dto.endereco().getLogradouro());
				end.setNumero(dto.endereco().getNumero());
				end.setPrincipal(dto.endereco().getPrincipal());
			}
		}

	}

	public Endereco getPrincipal(List<Endereco> endereco) {
		for (Endereco end : endereco) {
			if (end.getPrincipal() == true) {
				return end;
			}
		}
		return null;
	}
	
	public Endereco naoFavoritoEndereco(@Valid List<Endereco> endereco, Endereco end) {
		for (Endereco list : endereco) {
			if (list.getPrincipal() == true && end.getPrincipal() == true) {
				return null;
			}
		}
		return end;
	}
}
