package br.com.gs.projeto_agro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.gs.projeto_agro.model.Alerta;
import br.com.gs.projeto_agro.repository.AlertaRepository;

@Service
public class AlertaCachingService {

	@Autowired
    private AlertaRepository repoA;

    @Cacheable(value = "alerta_paginados", key = "#pr")
    public Page<Alerta> findAll(PageRequest pr) {
        return repoA.findAll(pr);
    }

    @Cacheable(
    		value = "alerta_substring",
    		key = "#substring"
    		)
    public List<Alerta> findByTipo(String substring) {
        return repoA.findByTipoContainingIgnoreCase(substring);
    }
    
    @Cacheable(value = "alerta_status", key = "#status")
    public List<Alerta> findByStatus(String status) {
        return repoA.findByStatus(status);
    }

    @Cacheable(value = "alerta_all")
    public List<Alerta> findAll() {
        return repoA.findAll();
    }

    @Cacheable(value = "alerta_id", key = "#id")
    public Optional<Alerta> findById(Integer id) {
        return repoA.findById(id);
    }

    @CacheEvict(value = {
            "alerta_paginados",
            "alerta_status",
            "alerta_all",
            "alerta_id"
    }, allEntries = true)
    public void removerCache() {
        System.out.println("Cache Alerta removido");
    }
	
}
