package br.com.Attornatus.desafio.util.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.Attornatus.desafio.model.Endereco;
import br.com.Attornatus.desafio.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	
	@Query("SELECT p.endereco FROM Pessoa p WHERE p.id = :id")
	Page<Endereco> findEnderecosPessoa(Pageable page, Long id);
}
