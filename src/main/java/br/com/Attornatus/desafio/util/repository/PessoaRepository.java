package br.com.Attornatus.desafio.util.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.Attornatus.desafio.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
