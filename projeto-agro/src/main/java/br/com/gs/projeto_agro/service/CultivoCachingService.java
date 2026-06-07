package br.com.gs.projeto_agro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.gs.projeto_agro.model.Cultivo;
import br.com.gs.projeto_agro.repository.CultivoRepository;

@Service
public class CultivoCachingService {

	@Autowired
    private CultivoRepository repoC;

    @Cacheable(
    		value = "cultivo_paginados",
    		key = "#pr"
    		)
    public Page<Cultivo> findAll(PageRequest pr) {
        return repoC.findAll(pr);
    }

   
    @Cacheable(
    		value = "cultivo_substring_status",
    		key = "#substring")
    
    public List<Cultivo> findByStatusSubstring(String substring) {
        return repoC.findByStatus(substring);
    }

    @Cacheable(
    		value = "cultivo_local", 
    		key = "#localId"
    		)
    public List<Cultivo> findByLocal(Integer localId) {
        return repoC.findByLocal_Id(localId);
    }
    
    @Cacheable(
    		value = "cultivo_cultura",
    		key = "#culturaId"
    		)
    public List<Cultivo> findByCultura(Integer culturaId) {
        return repoC.findByCultura_Id(culturaId);
    }
    
    @Cacheable(value = "cultivo_all")
    public List<Cultivo> findAll() {
        return repoC.findAll();
    }

    @Cacheable(
    		value = "cultivo_id",
    		key = "#id"
    		)
    public Optional<Cultivo> findById(Integer id) {
        return repoC.findById(id);
    }

    @CacheEvict(value = {
            "cultivo_paginados",
            "cultivo_substring",
            "cultivo_local",
            "cultivo_status",
            "cultivo_all",
            "cultivo_id"
    }, allEntries = true)
    public void removerCache() {
        System.out.println("Cache Cultivo removido");
    }
}
