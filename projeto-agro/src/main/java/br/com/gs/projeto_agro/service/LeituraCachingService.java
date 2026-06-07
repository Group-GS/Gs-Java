package br.com.gs.projeto_agro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.gs.projeto_agro.model.Leitura;
import br.com.gs.projeto_agro.repository.LeituraRepository;

@Service
public class LeituraCachingService {

	@Autowired
    private LeituraRepository repoL;

    @Cacheable(
    		value = "leitura_paginados",
    		key = "#pr"
    		)
    public Page<Leitura> findAll(PageRequest pr) {
        return repoL.findAll(pr);
    }

    @Cacheable(
    		value = "leitura_substring",
    		key = "#substring"
    		)
    public List<Leitura> findByValor(Double substring) {
        return repoL.findByValor(substring);
    }
    
    @Cacheable(
    		value = "leitura_sensor",
    		key = "#sensorId"
    		)
    public List<Leitura> findBySensor(Integer sensorId) {
        return repoL.findBySensor_Id(sensorId);
    }

    @Cacheable(value = "leitura_all")
    public List<Leitura> findAll() {
        return repoL.findAll();
    }

    @Cacheable(
    		value = "leitura_id",
    		key = "#id"
    		)
    public Optional<Leitura> findById(Integer id) {
        return repoL.findById(id);
    }

    @CacheEvict(value = {
            "leitura_paginados",
            "leitura_substring",
            "leitura_sensor",
            "leitura_all",
            "leitura_id"
    }, allEntries = true)
    public void removerCache() {
        System.out.println("Cache Leitura removido");
    }
}
