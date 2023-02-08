package br.com.Attornatus.desafio.model;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.Attornatus.desafio.DTO.PessoaDTO;
import br.com.Attornatus.desafio.service.Acao;
import br.com.Attornatus.desafio.util.Conversor;
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
	private Date dataNascimento;

	@Valid
	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Endereco> endereco = new ArrayList<>();;

	public Pessoa(PessoaDTO dto) throws ParseException {
		this.nome = dto.nome();
		this.dataNascimento = Conversor.toDate(dto.dataNascimento());
		this.endereco.add(dto.endereco());
	}
	
	public void atualizar(PessoaDTO dto) throws ParseException {
		this.nome = dto.nome();
		this.dataNascimento = Conversor.toDate(dto.dataNascimento());
		new Acao().atualizarEndereco(endereco, dto);
	}

}
