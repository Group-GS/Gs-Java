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
import br.com.gs.projeto_agro.repository.LeituraRepository;
import br.com.gs.projeto_agro.service.LeituraCachingService;
import br.com.gs.projeto_agro.service.LeituraPaginacaoService;
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
	
	@GetMapping("/paginados")
	public ResponseEntity<Page<LeituraDTO>> paginar	(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
				
			@RequestParam(value = "size", defaultValue = "2") Integer size)
				
				 
			{ PageRequest req = PageRequest.of(page, size);
				 
			Page<LeituraDTO> paginados = paginacaoL.paginar(req);
			return ResponseEntity.ok(paginados);
				 
				 }	
	

@GetMapping(value="/todos")
	public List<Leitura>retornarTodos(){
		return cachingL.findAll();
	}

@GetMapping(value="/{id}")
	public Leitura retornarLeituraPorId(@PathVariable @Valid Integer id) {
		
		Optional<Leitura> op = cachingL.findById(id);
		
		if(op.isPresent()) {
			return op.get();
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
	}
@PostMapping(value="/novo")
	public Leitura inserirLeitura(@RequestBody @Valid Leitura leitura ) {
		
		repoL.save(leitura);
		cachingL.removerCache();
		
		return leitura;
	}

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
