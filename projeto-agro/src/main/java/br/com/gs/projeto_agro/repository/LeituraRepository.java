package br.com.gs.projeto_agro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gs.projeto_agro.model.Leitura;

public interface LeituraRepository extends JpaRepository<Leitura,Integer> {

	List<Leitura> findByValor(Double valor);

	List<Leitura> findBySensor_Id(Integer idSensor);
}
