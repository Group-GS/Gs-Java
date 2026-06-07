package br.com.gs.projeto_agro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.gs.projeto_agro.dto.CultivoDTO;
import br.com.gs.projeto_agro.model.Cultivo;
import br.com.gs.projeto_agro.repository.CultivoRepository;



@Service
public class CultivoPaginacaoService {

	@Autowired
	private CultivoRepository repoC;
	
	@Transactional(readOnly= true)
	public Page<CultivoDTO> paginar(PageRequest req){
		
		
		Page<Cultivo>paginasCultivo=
				repoC.findAll(req);
		Page<CultivoDTO>paginasCultivoDTO=
				paginasCultivo.map(
						cultivo -> new CultivoDTO(cultivo));
				
		return paginasCultivoDTO;
	}
	
}
