package br.com.gs.projeto_agro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.gs.projeto_agro.model.Cultura;
import br.com.gs.projeto_agro.repository.CulturaRepository;

@Service
public class CulturaCachingService {

	@Autowired
    private CulturaRepository repoC;

    @Cacheable(
    		value = "cultura_paginados",
    		key = "#pr"
    		)
    public Page<Cultura> findAll(PageRequest pr) {
        return repoC.findAll(pr);
    }

    @Cacheable(
    		value = "cultura_substring",
    		key = "#substring"
    		)
    public List<Cultura> findByNome(String substring) {
        return repoC.findByNomeContainingIgnoreCase(substring);
    }

    @Cacheable(value = "cultura_all")
    public List<Cultura> findAll() {
        return repoC.findAll();
    }

    @Cacheable(
    		value = "cultura_id",
    		key = "#id"
    		)
    public Optional<Cultura> findById(Integer id) {
        return repoC.findById(id);
    }

    @CacheEvict(value = {
            "cultura_paginados",
            "cultura_substring",
            "cultura_all",
            "cultura_id"
    }, allEntries = true)
    public void removerCache() {
        System.out.println("Cache Cultura removido");
    }
}
