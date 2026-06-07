package br.com.gs.projeto_agro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.gs.projeto_agro.model.Local;
import br.com.gs.projeto_agro.repository.LocalRepository;

@Service
public class LocalCachingService {

	@Autowired
    private LocalRepository repoL;

    @Cacheable(
    		value = "local_paginados",
    		key = "#pr"
    		)
    public Page<Local> findAll(PageRequest pr) {
        return repoL.findAll(pr);
    }

    @Cacheable(
    		value = "local_substring", 
    		key = "#substring"
    		)
    public List<Local> findByNome(String substring) {
        return repoL.findByNomeContainingIgnoreCase(substring);
    }

    @Cacheable(
    		value = "local_bioma",
    		key = "#idBioma"
    		)
    public List<Local> findByBioma(Integer idBioma) {
        return repoL.findByBioma_IdBioma(idBioma);
    }

    @Cacheable(value = "local_all")
    public List<Local> findAll() {
        return repoL.findAll();
    }

    @Cacheable(
    		value = "local_id",
    		key = "#id"
    		)
    public Optional<Local> findById(Integer id) {
        return repoL.findById(id);
    }

    @CacheEvict(value = {
            "local_paginados",
            "local_substring",
            "local_bioma",
            "local_all",
            "local_id"
    }, allEntries = true)
    public void removerCache() {
        System.out.println("Cache Local removido");
    }
}
