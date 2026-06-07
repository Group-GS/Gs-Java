package br.com.gs.projeto_agro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.gs.projeto_agro.dto.AlertaDTO;
import br.com.gs.projeto_agro.model.Alerta;
import br.com.gs.projeto_agro.repository.AlertaRepository;

@Service
public class AlertaPaginacaoService {

	@Autowired
	private AlertaRepository repoA;
	
	@Transactional(readOnly= true)
	public Page<AlertaDTO> paginar(PageRequest req){
		
		
		Page<Alerta>paginasAlerta=
				repoA.findAll(req);
		Page<AlertaDTO>paginasAlertaDTO=
				paginasAlerta.map(
						alerta -> new AlertaDTO(alerta));
				
		return paginasAlertaDTO;
	}
}
