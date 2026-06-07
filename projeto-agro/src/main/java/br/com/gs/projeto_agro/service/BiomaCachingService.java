package br.com.gs.projeto_agro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.gs.projeto_agro.model.Bioma;
import br.com.gs.projeto_agro.repository.BiomaRepository;

@Service
public class BiomaCachingService {

	@Autowired
    private BiomaRepository repoB;

    @Cacheable(
    		value = "bioma_paginados",
    		key = "#pr"
    		)
    public Page<Bioma> findAll(PageRequest pr) {
        return repoB.findAll(pr);
    }

    @Cacheable(
    		value = "bioma_substring",
    		key = "#substring"
    		)
    public List<Bioma> findByNome(String substring) {
        return repoB.findByNomeContainingIgnoreCase(substring);
    }

    @Cacheable(value = "bioma_all")
    public List<Bioma> findAll() {
        return repoB.findAll();
    }

    @Cacheable(
    		value = "bioma_id",
    		key = "#id"
    		)
    public Optional<Bioma> findById(Integer id) {
        return repoB.findById(id);
    }

    @CacheEvict(value = {
            "bioma_paginados",
            "bioma_substring",
            "bioma_all",
            "bioma_id"
    }, allEntries = true)
    public void removerCache() {
        System.out.println("Cache Bioma removido");
    }
}

