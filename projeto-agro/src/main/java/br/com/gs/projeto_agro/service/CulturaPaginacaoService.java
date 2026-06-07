package br.com.gs.projeto_agro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.gs.projeto_agro.dto.CulturaDTO;
import br.com.gs.projeto_agro.model.Cultura;
import br.com.gs.projeto_agro.repository.CulturaRepository;

@Service
public class CulturaPaginacaoService {

	@Autowired
	private CulturaRepository repoC;
	
	@Transactional(readOnly= true)
	public Page<CulturaDTO> paginar(PageRequest req){
		
		
		Page<Cultura>paginasCultura=
				repoC.findAll(req);
		Page<CulturaDTO>paginasCulturaDTO=
				paginasCultura.map(
						cultura -> new CulturaDTO(cultura));
				
		return paginasCulturaDTO;
	}
}
