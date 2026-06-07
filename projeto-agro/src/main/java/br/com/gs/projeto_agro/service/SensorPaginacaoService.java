package br.com.gs.projeto_agro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.gs.projeto_agro.dto.SensorDTO;
import br.com.gs.projeto_agro.model.Sensor;
import br.com.gs.projeto_agro.repository.SensorRepository;

@Service
public class SensorPaginacaoService {

	@Autowired
	private SensorRepository repoS;
	
	@Transactional(readOnly= true)
	public Page<SensorDTO> paginar(PageRequest req){
		
		
		Page<Sensor>paginasSensor=
				repoS.findAll(req);
		Page<SensorDTO>paginasSensorDTO=
				paginasSensor.map(
						sensor -> new SensorDTO(sensor));
				
		return paginasSensorDTO;
	}
}
