package br.com.gs.projeto_agro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.gs.projeto_agro.model.Usuario;
import br.com.gs.projeto_agro.repository.UsuarioRepository;

@Service
public class UsuarioCachingService {

	@Autowired
    private UsuarioRepository repoU;

    @Cacheable(
    		value = "usuario_paginados",
    		key = "#pr"
    		)
    public Page<Usuario> findAll(PageRequest pr) {
        return repoU.findAll(pr);
    }

    @Cacheable(
    		value = "usuario_substring",
    		key = "#substring"
    		)
    public List<Usuario> findByNome(String substring) {
        return repoU.findByNomeContainingIgnoreCase(substring);
    }

    @Cacheable(
    		value = "usuario_email",
    		key = "#email"
    		)
    public Optional<Usuario> findByEmail(String email) {
        return repoU.findByEmail(email);
    }

    @Cacheable(value = "usuario_all")
    public List<Usuario> findAll() {
        return repoU.findAll();
    }

    @Cacheable(
    		value = "usuario_id",
    		key = "#id"
    		)
    public Optional<Usuario> findById(Integer id) {
        return repoU.findById(id);
    }

    @CacheEvict(value = {
            "usuario_paginados",
            "usuario_substring",
            "usuario_email",
            "usuario_all",
            "usuario_id"
    }, allEntries = true)
    public void removerCache() {
        System.out.println("Cache Usuario removido");
    }
}
