package br.com.Attornatus.desafio.model;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.Attornatus.desafio.DTO.pessoa.PessoaDTO;
import br.com.Attornatus.desafio.service.Acao;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pessoas")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private LocalDate dataNascimento;

	@Valid
	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Endereco> endereco = new ArrayList<>();;

	public Pessoa(PessoaDTO dto) throws ParseException {
		this.nome = dto.nome();
		this.dataNascimento = dto.dataNascimento();
		this.endereco.add(dto.endereco());
	}

	public void atualizar(PessoaDTO dto) throws ParseException {
		this.nome = dto.nome();
		this.dataNascimento = dto.dataNascimento();
		new Acao().atualizarEndereco(endereco, dto);
	}

}
