package br.com.gs.projeto_agro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gs.projeto_agro.model.Cultivo;

public interface CultivoRepository extends JpaRepository<Cultivo, Integer>{

	

    List<Cultivo> findByStatus(String status);

    List<Cultivo> findByLocal_Id(Integer localId);

    List<Cultivo> findByCultura_Id(Integer culturaId);


}
