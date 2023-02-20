package br.com.Attornatus.desafio.service;

import java.text.ParseException;
import java.util.List;

import br.com.Attornatus.desafio.DTO.pessoa.PessoaDTO;
import br.com.Attornatus.desafio.model.Endereco;
import jakarta.servlet.ServletException;
import jakarta.validation.Valid;

public class Acao {
	public void adicionarEndereco(List<Endereco> endereco, Endereco end) throws ParseException, ServletException {
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

	public Endereco getPrincipal(List<Endereco> endereco) throws ServletException {
		for (Endereco end : endereco) {
			if (end.getPrincipal() == true) {
				return end;
			}
		}
		throw new ServletException("Não há nenhum endereço principal");
	}
	
	private Endereco naoFavoritoEndereco(@Valid List<Endereco> endereco, Endereco end) throws ServletException {
		for (Endereco list : endereco) {
			if (list.getPrincipal() == true && end.getPrincipal() == true) {
				 throw new ServletException("Não é possível ter mais de um enderço sendo favorito");
			}
		}
		return end;
	}
}
