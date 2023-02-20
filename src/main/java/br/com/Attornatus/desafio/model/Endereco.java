package br.com.Attornatus.desafio.model;

import br.com.Attornatus.desafio.DTO.endereco.EnderecoDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "enderecos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String logradouro;
	private String cep;
	private String numero;
	private String cidade;
	private Boolean principal;

	public Endereco(EnderecoDTO dto) {
		this.cep = dto.cep();
		this.cidade = dto.cidade();
		this.logradouro = dto.logradouro();
		this.numero = dto.numero();
		this.principal = dto.principal();
		
	}

	public Endereco(String logradouro, String cep, String numero, String cidade, Boolean principal) {
		super();
		this.logradouro = logradouro;
		this.cep = cep;
		this.numero = numero;
		this.cidade = cidade;
		this.principal = principal;
	}
	
	
}
