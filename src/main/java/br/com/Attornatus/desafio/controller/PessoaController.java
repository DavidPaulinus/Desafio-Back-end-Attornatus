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

import br.com.Attornatus.desafio.DTO.endereco.EnderecoDTO;
import br.com.Attornatus.desafio.DTO.endereco.EnderecoDetalhamentoDTO;
import br.com.Attornatus.desafio.DTO.endereco.EnderecoListarDTO;
import br.com.Attornatus.desafio.DTO.pessoa.PessoaDTO;
import br.com.Attornatus.desafio.DTO.pessoa.PessoaDetalhamentoDTO;
import br.com.Attornatus.desafio.DTO.pessoa.PessoaListarDTO;
import br.com.Attornatus.desafio.model.Pessoa;
import br.com.Attornatus.desafio.service.PessoaService;
import jakarta.servlet.ServletException;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

	@Autowired
	private PessoaService serv;

	@PostMapping
	@Transactional
	public ResponseEntity<PessoaDetalhamentoDTO> criarPessoa(@RequestBody PessoaDTO dto, UriComponentsBuilder uri) throws ParseException {
		var pessoa = new Pessoa(dto);
		serv.salvarPessoa(pessoa);

		return ResponseEntity.created(uri.path("/pessoa/{id}").buildAndExpand(pessoa.getId()).toUri()).body(new PessoaDetalhamentoDTO(pessoa));
	}

	@GetMapping
	public ResponseEntity<Page<PessoaListarDTO>> listarPessoas(@PageableDefault(sort = { "nome" }) Pageable page) {
		return ResponseEntity.ok(serv.listarPessoa(page).map(PessoaListarDTO::new));
	}

	@GetMapping("/{id}")
	public ResponseEntity<PessoaDetalhamentoDTO> consultarPessoa(@PathVariable Long id) {
		return ResponseEntity.ok(new PessoaDetalhamentoDTO(serv.detalharPessoaPorId(id)));
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<PessoaDetalhamentoDTO> editarPessoa(@RequestBody PessoaDTO dto, @PathVariable Long id)throws ParseException {
		var pessoa = serv.atualizarPessoa(id, dto);

		return ResponseEntity.ok(new PessoaDetalhamentoDTO(pessoa));
	}

	// Endereço

	@PostMapping("endereco/{id}")
	@Transactional
	public ResponseEntity<PessoaDetalhamentoDTO> criarEnderecoPessoa(@PathVariable Long id, @RequestBody EnderecoDTO dto,UriComponentsBuilder uri) throws ParseException, ServletException {
		var pessoa = serv.adicionarEndereco(id, dto);

		return ResponseEntity.created(uri.path("/pessoas/{id}").buildAndExpand(id).toUri()).body(new PessoaDetalhamentoDTO(pessoa));
	}

	@GetMapping("/endereco/{id}")
	public ResponseEntity<Page<EnderecoListarDTO>> listarEnderecosPessoa(@PageableDefault(sort = { "nome" }) Pageable page, @PathVariable Long id) {
		return ResponseEntity.ok(serv.listarEnderecos(page, id).map(EnderecoListarDTO::new));
	}

	@GetMapping("/endereco/principal/{id}")
	public ResponseEntity<EnderecoDetalhamentoDTO> consultarEnderecoPrincipal(@PathVariable Long id) throws ServletException {
		return ResponseEntity.ok(new EnderecoDetalhamentoDTO(serv.enderecoPrincipal(id)));
	}

}
