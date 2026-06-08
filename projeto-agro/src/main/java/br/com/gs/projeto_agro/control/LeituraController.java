package br.com.gs.projeto_agro.control;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.gs.projeto_agro.dto.LeituraDTO;
import br.com.gs.projeto_agro.model.Leitura;
import br.com.gs.projeto_agro.model.Local;
import br.com.gs.projeto_agro.model.Sensor;
import br.com.gs.projeto_agro.repository.LeituraRepository;
import br.com.gs.projeto_agro.service.LeituraCachingService;
import br.com.gs.projeto_agro.service.LeituraPaginacaoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value="/leitura")
public class LeituraController {

	@Autowired
	private LeituraRepository repoL;
	
	@Autowired
	private LeituraPaginacaoService paginacaoL;
	
	@Autowired
	private LeituraCachingService cachingL;
	
	
	@Operation(
            summary = "Listar Leitura paginados",
            description = "Retorna Leitura em formato paginado com base em page e size",
            tags = "Retorno de informações da Leitura"
        )
	@GetMapping("/paginados")
	public ResponseEntity<Page<LeituraDTO>> paginar	(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
				
			@RequestParam(value = "size", defaultValue = "2") Integer size)
				
				 
			{ PageRequest req = PageRequest.of(page, size);
				 
			Page<LeituraDTO> paginados = paginacaoL.paginar(req);
			return ResponseEntity.ok(paginados);
				 
				 }	
	@Operation(
            summary = "Buscar Leitura por substring",
            description = "Busca Leiutra filtrando por parte do nome ou outros campos",
            tags = "Retorno de informações da Leitura"
        )
@GetMapping("/substring")
	public List<Leitura> retornarLeituraPorSubstring(@RequestParam Double substring) {
	    return cachingL.findByValor(substring);
	}
	@Operation(
            summary = "Buscar Leitura por Sensor",
            description = "Retorna lista de Leitura filtrados pelo Sensor",
            tags = "Retorno de informações da Leitura"
        )
	@GetMapping("/sensor")
    public List<Leitura> retornarLeituraPorSensor(@RequestParam Integer sensorId) {
        return cachingL.findBySensor(sensorId);
    }
	
	@Operation(
            summary = "Listar todos as Leituras (cache)",
            description = "Retorna todas as Leituras utilizando cache para performance",
            tags = "Retorno de informações da Leitura"
        )
@GetMapping(value="/todos")
	public List<Leitura>retornarTodos(){
		return cachingL.findAll();
	}
	@Operation(
            summary = "Buscar Leitura por ID",
            description = "Retorna uma Leitura específico pelo ID",
            tags = "Retorno de informações da Leitura"
        )
@GetMapping(value="/{id}")
	public Leitura retornarLeituraPorId(@PathVariable @Valid Integer id) {
		
		Optional<Leitura> op = cachingL.findById(id);
		
		if(op.isPresent()) {
			return op.get();
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
	}
	@Operation(
            summary = "Criar nova Leitura",
            description = "Insere uma nova Leitura  no banco e limpa cache",
            tags = "Inserção de informações da Leitura"
        )
@PostMapping(value="/novo")
	public Leitura inserirLeitura(@RequestBody @Valid Leitura leitura ) {
		
		repoL.save(leitura);
		cachingL.removerCache();
		
		return leitura;
	}
	@Operation(
            summary = "Remover Leitura",
            description = "Remove uma Leitura pelo ID e limpa cache",
            tags = "Remoção de informações da Leitura"
        )
@DeleteMapping(value="remover/{id}")
	public Leitura deletarLeitura(@PathVariable @Valid Integer id) {
		
		Optional<Leitura> op = repoL.findById(id);
		
		
		if(op.isPresent()) {
			
			repoL.delete(op.get());
			cachingL.removerCache();
			
			
			return op.get();
			
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
	}
	@Operation(
            summary = "Atualizar Leitura",
            description = "Atualiza dados de uma Leitura existente",
            tags = "Atualização dos dados da Leitura"
        )
@PutMapping(value="atualizar/{id}")
	public Leitura atualizarLeitura(@PathVariable Integer id,@RequestBody @Valid Leitura leitura) {
		
		Optional<Leitura> op = repoL.findById(id);
		
		if(op.isPresent()) {
			
			Leitura leituraBanco = op.get();
			leituraBanco.transferirLeitura(leitura);
			repoL.save(leituraBanco);
			cachingL.removerCache();
			return leituraBanco;
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
	}









	
}
