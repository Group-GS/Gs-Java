package br.com.gs.projeto_agro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gs.projeto_agro.model.Sensor;

public interface SensorRepository extends JpaRepository<Sensor,Integer> {

	 List<Sensor> findByStatus(String status);

	
	 List<Sensor> findByTipoSensorContainingIgnoreCase(String tipoSensor);
}
