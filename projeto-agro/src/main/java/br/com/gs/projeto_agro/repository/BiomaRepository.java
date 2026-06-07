package br.com.gs.projeto_agro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gs.projeto_agro.model.Bioma;

public interface BiomaRepository  extends JpaRepository<Bioma, Integer>{

	List<Bioma> findByNomeContainingIgnoreCase(String nome);
}
