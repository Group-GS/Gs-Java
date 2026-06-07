package br.com.gs.projeto_agro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.gs.projeto_agro.dto.UsuarioDTO;
import br.com.gs.projeto_agro.model.Usuario;
import br.com.gs.projeto_agro.repository.UsuarioRepository;


@Service
public class UsuarioPaginacaoService {

	@Autowired
	private UsuarioRepository repoU;
	
	@Transactional(readOnly= true)
	public Page<UsuarioDTO> paginar(PageRequest req){
		
		
		Page<Usuario>paginasUsuario=
				repoU.findAll(req);
		Page<UsuarioDTO>paginasUsuarioDTO=
				paginasUsuario.map(
						usuario -> new UsuarioDTO(usuario));
				
		return paginasUsuarioDTO;
	}
	
	
}
