package br.com.gs.projeto_agro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.gs.projeto_agro.model.Sensor;
import br.com.gs.projeto_agro.repository.SensorRepository;

@Service
public class SensorCachingService {

	 @Autowired
	    private SensorRepository repoS;

	    @Cacheable(
	    		value = "sensor_paginados",
	    		key = "#pr"
	    		)
	    public Page<Sensor> findAll(PageRequest pr) {
	        return repoS.findAll(pr);
	    }

	    @Cacheable(
	    		value = "sensor_substring",
	    		key = "#substring"
	    		)
	    public List<Sensor> findByTipo(String substring) {
	        return repoS.findByTipoSensorContainingIgnoreCase(substring);
	    }
	    
	    @Cacheable(
	    		value = "sensor_status",
	    		key = "#status"
	    		)
	    public List<Sensor> findByStatus(String status) {
	        return repoS.findByStatus(status);
	    }

	    @Cacheable(value = "sensor_all")
	    public List<Sensor> findAll() {
	        return repoS.findAll();
	    }

	    @Cacheable(
	    		value = "sensor_id",
	    		key = "#id"
	    		)
	    public Optional<Sensor> findById(Integer id) {
	        return repoS.findById(id);
	    }

	    @CacheEvict(value = {
	            "sensor_paginados",
	            "sensor_substring",
	            "sensor_status",
	            "sensor_all",
	            "sensor_id"
	    }, allEntries = true)
	    public void removerCache() {
	        System.out.println("Cache Sensor removido");
	    }
	
}
