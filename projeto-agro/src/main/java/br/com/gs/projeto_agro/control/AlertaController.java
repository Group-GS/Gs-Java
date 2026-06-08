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

import br.com.gs.projeto_agro.dto.AlertaDTO;
import br.com.gs.projeto_agro.model.Alerta;
import br.com.gs.projeto_agro.repository.AlertaRepository;
import br.com.gs.projeto_agro.service.AlertaCachingService;
import br.com.gs.projeto_agro.service.AlertaPaginacaoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value="/alerta")
public class AlertaController {

	@Autowired
	private AlertaRepository repoA;
	
	@Autowired
	private AlertaPaginacaoService paginacaoA;
	
	@Autowired
	private AlertaCachingService cachingA;

	@Operation(
            summary = "Listar Alerta paginados",
            description = "Retorna Alerta em formato paginado com base em page e size",
            tags = "Retorno de informações do Alerta"
        )
	@GetMapping("/paginados")
	public ResponseEntity<Page<AlertaDTO>> paginar	(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
				
			@RequestParam(value = "size", defaultValue = "2") Integer size)
				
				 
			{ PageRequest req = PageRequest.of(page, size);
				 
			Page<AlertaDTO> paginados = paginacaoA.paginar(req);
			return ResponseEntity.ok(paginados);
				 
				 }
	
	@Operation(
            summary = "Buscar Alerta por substring",
            description = "Busca Alerta filtrando por parte do nome ou outros campos via projection",
            tags = "Retorno de informações do Alerta"
        )
@GetMapping("/substring")
	  public List<Alerta> retornarAlertaPorSubstring(@RequestParam String substring) {
	       return cachingA.findByTipo(substring);
	   }  
	@Operation(
            summary = "Buscar Alerta por Status",
            description = "Retorna lista de Alertas filtrados pelo Status",
            tags = "Retorno de informações do Alerta"
        )
@GetMapping("/por_status")
	  public List<Alerta> retornarAlertaPorStatus(@RequestParam String status) {
	      return cachingA.findByStatus(status);
	   }
@Operation(
        summary = "Listar todos os Alertas (cache)",
        description = "Retorna todos os Alertas utilizando cache para performance",
        tags = "Retorno de informações do Alerta"
    )
@GetMapping(value="/todos")
	public List<Alerta>retornarTodosAlertas(){
		return cachingA.findAll();
	}
@Operation(
        summary = "Buscar Alerta por ID",
        description = "Retorna um Alerta específico pelo ID",
        tags = "Retorno de informações do Alerta"
    )
@GetMapping(value="/{id}")
	public Alerta retornarAlertaPorId(@PathVariable @Valid Integer id) {
		
		Optional<Alerta> op = cachingA.findById(id);
		
		if(op.isPresent()) {
			return op.get();
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
	}
@Operation(
        summary = "Criar novo Alerta",
        description = "Insere um novo Alerta no banco e limpa cache",
        tags = "Inserção de informações do Alerta"
    )
@PostMapping(value="/novo")
	public Alerta inserirAlerta(@RequestBody @Valid Alerta alerta ) {
		
		repoA.save(alerta);
		cachingA.removerCache();
		
		return alerta ;
	}
@Operation(
        summary = "Remover Alerta",
        description = "Remove um Alerta pelo ID e limpa cache",
        tags = "Remoção de informações do Alerta"
    )

@DeleteMapping(value="remover/{id}")
	public Alerta deletarAlerta(@PathVariable @Valid Integer id) {
		
		Optional<Alerta> op = repoA.findById(id);
		
		
		if(op.isPresent()) {
			
			repoA.delete(op.get());
			cachingA.removerCache();
			
			return op.get();
			
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
	}
@Operation(
        summary = "Atualizar Alerta",
        description = "Atualiza dados de um Alerta existente",
        tags = "Atualização dos dados do Alerta"
    )
@PutMapping(value="atualizar/{id}")
	public Alerta atualizarAlerta(@PathVariable Integer id,@RequestBody @Valid Alerta alerta ) {
		
		Optional<Alerta> op = repoA.findById(id);
		
		if(op.isPresent()) {
			
			Alerta alertaBanco = op.get();
			alertaBanco.transferirAlerta(alerta);
			repoA.save(alertaBanco);
			cachingA.removerCache();
			return alertaBanco;
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
	}





}
