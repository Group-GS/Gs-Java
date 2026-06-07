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

	@GetMapping("/paginados")
	public ResponseEntity<Page<AlertaDTO>> paginar	(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
				
			@RequestParam(value = "size", defaultValue = "2") Integer size)
				
				 
			{ PageRequest req = PageRequest.of(page, size);
				 
			Page<AlertaDTO> paginados = paginacaoA.paginar(req);
			return ResponseEntity.ok(paginados);
				 
				 }
@GetMapping("/substring")
	  public List<Alerta> retornarAlertaPorSubstring(@RequestParam String substring) {
	       return cachingA.findByTipo(substring);
	   }  

@GetMapping("/por_status")
	  public List<Alerta> retornarAlertaPorStatus(@RequestParam String status) {
	      return cachingA.findByStatus(status);
	   }
	
@GetMapping(value="/todos")
	public List<Alerta>retornarTodosAlertas(){
		return cachingA.findAll();
	}

@GetMapping(value="/{id}")
	public Alerta retornarAlertaPorId(@PathVariable @Valid Integer id) {
		
		Optional<Alerta> op = cachingA.findById(id);
		
		if(op.isPresent()) {
			return op.get();
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
	}
@PostMapping(value="/novo")
	public Alerta inserirAlerta(@RequestBody @Valid Alerta alerta ) {
		
		repoA.save(alerta);
		cachingA.removerCache();
		
		return alerta ;
	}

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
