package br.com.Attornatus.desafio.service;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.Attornatus.desafio.DTO.endereco.EnderecoDTO;
import br.com.Attornatus.desafio.DTO.pessoa.PessoaDTO;
import br.com.Attornatus.desafio.model.Endereco;
import br.com.Attornatus.desafio.model.Pessoa;
import br.com.Attornatus.desafio.util.repository.PessoaRepository;
import jakarta.servlet.ServletException;

@Service
public class PessoaService {
	
	private PessoaRepository pRep;
	
	private Acao ac = new Acao();

	@Autowired
	public PessoaService(PessoaRepository rep) {
		this.pRep = rep;
	}

	public void salvarPessoa(Pessoa pessoa) {
		pRep.save(pessoa);
	}

	public Page<Pessoa> listarPessoa(Pageable page) {
		return pRep.findAll(page);
	}

	public Pessoa detalharPessoaPorId(Long id) {
		return pRep.getReferenceById(id);
	}

	public Page<Endereco> listarEnderecos(Pageable page, Long id) {
		return pRep.findEnderecosPessoa(page, id);
	}

	public Endereco enderecoPrincipal(Long id) throws ServletException {
		var pessoa = pRep.getReferenceById(id);
		return ac.getPrincipal(pessoa.getEndereco());
	}

	public Pessoa adicionarEndereco(Long id, EnderecoDTO dto) throws ParseException, ServletException {
		var pessoa = pRep.getReferenceById(id);
		ac.adicionarEndereco(pessoa.getEndereco(), new Endereco(dto));
		
		return pessoa;
	}

	public Pessoa atualizarPessoa(Long id, PessoaDTO dto) throws ParseException {
		var pessoa = pRep.getReferenceById(id);
		pessoa.atualizar(dto);
		
		return pessoa;
	}

}
