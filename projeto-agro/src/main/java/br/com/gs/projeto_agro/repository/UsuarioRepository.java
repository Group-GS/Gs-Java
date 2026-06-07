package br.com.gs.projeto_agro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gs.projeto_agro.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{

	 	Optional<Usuario> findByEmail(String email);

	    List<Usuario> findByNomeContainingIgnoreCase(String nome);

}
