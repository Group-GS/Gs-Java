package br.com.gs.projeto_agro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gs.projeto_agro.model.Alerta;

public interface AlertaRepository extends JpaRepository<Alerta,Integer> {

	  List<Alerta> findByStatus(String status);

	  List<Alerta> findByLocal_Id(Integer localId);
	  
	  List<Alerta> findByTipoContainingIgnoreCase(String tipo);
	
}
