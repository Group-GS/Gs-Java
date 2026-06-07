package br.com.gs.projeto_agro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.gs.projeto_agro.dto.LeituraDTO;
import br.com.gs.projeto_agro.model.Leitura;
import br.com.gs.projeto_agro.repository.LeituraRepository;


@Service
public class LeituraPaginacaoService {

	@Autowired
	private LeituraRepository repoL;
	
	@Transactional(readOnly= true)
	public Page<LeituraDTO> paginar(PageRequest req){
		
		
		Page<Leitura>paginasLeitura=
				repoL.findAll(req);
		Page<LeituraDTO>paginasLeituraDTO=
				paginasLeitura.map(
						leitura -> new LeituraDTO(leitura));
				
		return paginasLeituraDTO;
	}
}
