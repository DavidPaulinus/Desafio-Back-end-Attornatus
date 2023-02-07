package br.com.Attornatus.desafio.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.Attornatus.desafio.DTO.EnderecoDTO;
import br.com.Attornatus.desafio.DTO.EnderecoDetalhamentoDTO;
import br.com.Attornatus.desafio.DTO.EnderecoListarDTO;
import br.com.Attornatus.desafio.DTO.PessoaDTO;
import br.com.Attornatus.desafio.DTO.PessoaDetalhamentoDTO;
import br.com.Attornatus.desafio.DTO.PessoaListarDTO;
import br.com.Attornatus.desafio.model.Endereco;
import br.com.Attornatus.desafio.model.Pessoa;
import br.com.Attornatus.desafio.util.repository.PessoaRepository;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

	@Autowired
	private PessoaRepository pRep;

	@PostMapping
	@Transactional
	public ResponseEntity criarPessoa(@RequestBody PessoaDTO dto, UriComponentsBuilder uri) throws ParseException {
		System.out.println("\\Salvando");

		var pessoa = new Pessoa(dto);
		pRep.save(pessoa);

		System.out.println("/Salvado");

		return ResponseEntity.created(uri.path("/pessoa/{id}").buildAndExpand(pessoa.getId()).toUri())
				.body(new PessoaDetalhamentoDTO(pessoa));
	}

	@GetMapping
	public ResponseEntity<Page<PessoaListarDTO>> listarPessoas(@PageableDefault(sort = { "nome" }) Pageable page) {
		System.out.println("***Listando***");

		return ResponseEntity.ok(pRep.findAll(page).map(PessoaListarDTO::new));
	}

	@GetMapping("/{id}")
	public ResponseEntity<PessoaDetalhamentoDTO> consultarPessoa(@PathVariable Long id) {
		System.out.println("***Detalhando***");

		return ResponseEntity.ok(new PessoaDetalhamentoDTO(pRep.getReferenceById(id)));
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<PessoaDetalhamentoDTO> editarPessoa(@RequestBody PessoaDTO dto, @PathVariable Long id) throws ParseException {
		System.out.println("\\Editando Pessoa");
		
		var pessoa = pRep.getReferenceById(id);
		pessoa.atualizar(dto);
		
		System.out.println("/Editado Pessoa");
		
		return ResponseEntity.ok(new PessoaDetalhamentoDTO(pessoa));
	}
	
	//Endereço

	@PostMapping("endereco/{id}")
	@Transactional
	public ResponseEntity criarEnderecoPessoa(@PathVariable Long id, @RequestBody EnderecoDTO dto,
			UriComponentsBuilder uri) throws ParseException {
		System.out.println("\\Criando Endereço");

		var pessoa = pRep.getReferenceById(id);
		pessoa.adicionarEndereco(new Endereco(dto));

		System.out.println("/Criado Endereço");

		return ResponseEntity.created(uri.path("/pessoas/{id}").buildAndExpand(id).toUri())
				.body(new PessoaDetalhamentoDTO(pessoa));
	}

	@GetMapping("/endereco/{id}")
	public ResponseEntity<Page<EnderecoListarDTO>> listarEnderecosPessoa(
			@PageableDefault(sort = { "nome" }) Pageable page, @PathVariable Long id) {
		System.out.println("***Listando***");

		return ResponseEntity.ok(pRep.findEnderecosPessoa(page, id).map(EnderecoListarDTO::new));
	}

	@GetMapping("/endereco/principal/{id}")
	public ResponseEntity<EnderecoDetalhamentoDTO> consultarEnderecoPrincipal(@PathVariable Long id) {
		System.out.println("***Detalhando***");

		return ResponseEntity.ok(new EnderecoDetalhamentoDTO(pRep.getReferenceById(id).getPrincipal()));
	}

}
