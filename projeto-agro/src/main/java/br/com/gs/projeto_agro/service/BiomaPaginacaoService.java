package br.com.gs.projeto_agro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.gs.projeto_agro.dto.BiomaDTO;
import br.com.gs.projeto_agro.model.Bioma;
import br.com.gs.projeto_agro.repository.BiomaRepository;



@Service
public class BiomaPaginacaoService {

	@Autowired
	private BiomaRepository repoB;
	
	@Transactional(readOnly= true)
	public Page<BiomaDTO> paginar(PageRequest req){
		
		
		Page<Bioma>paginasBioma=
				repoB.findAll(req);
		Page<BiomaDTO>paginasBiomaDTO=
				paginasBioma.map(
						bioma -> new BiomaDTO(bioma));
				
		return paginasBiomaDTO;
	}
}
