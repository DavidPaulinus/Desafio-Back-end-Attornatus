package br.com.Attornatus.desafio.model;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.Attornatus.desafio.DTO.PessoaDTO;
import br.com.Attornatus.desafio.util.Conversor;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

	@NotBlank
	private String nome;

	@NotNull
	private Date dataNascimento;

	@NotNull
	@Valid
	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Endereco> endereco = new ArrayList<>();;

	public Pessoa(PessoaDTO dto) throws ParseException {
		this.nome = dto.nome();
		this.dataNascimento = Conversor.toDate(dto.dataNascimento());
		endereco.add(dto.endereco());
	}
}
