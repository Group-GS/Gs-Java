package br.com.gs.projeto_agro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gs.projeto_agro.model.Local;

public interface LocalRepository extends JpaRepository<Local, Integer> {
 
	List<Local> findByNomeContainingIgnoreCase(String nome);

    List<Local> findByBioma_IdBioma(Integer idBioma);
	
}
