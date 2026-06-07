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

import br.com.gs.projeto_agro.dto.CulturaDTO;
import br.com.gs.projeto_agro.model.Cultura;
import br.com.gs.projeto_agro.repository.CulturaRepository;
import br.com.gs.projeto_agro.service.CulturaCachingService;
import br.com.gs.projeto_agro.service.CulturaPaginacaoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value="/cultura")
public class CulturaController {

	@Autowired
	private CulturaRepository repoC;
	
	@Autowired
	private CulturaPaginacaoService paginacaoC;
	
	@Autowired
	private CulturaCachingService cachingC;
	
@GetMapping("/paginados")
	public ResponseEntity<Page<CulturaDTO>> paginar	(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
				
			@RequestParam(value = "size", defaultValue = "2") Integer size)
				
				 
			{ PageRequest req = PageRequest.of(page, size);
				 
			Page<CulturaDTO> paginados = paginacaoC.paginar(req);
			return ResponseEntity.ok(paginados);
				 
				 }

 @GetMapping("/substring")
    public List<Cultura> retornarCulturaPorSubstring(@RequestParam String substring) {
        return cachingC.findByNome(substring);
    }
	
	
@GetMapping(value="/todos")
	public List<Cultura>retornarTodasCulturas(){
		return cachingC.findAll();
	}

@GetMapping(value="/{id}")
	public Cultura retornarCulturaPorId(@PathVariable @Valid Integer id) {
		
		Optional<Cultura> op = cachingC.findById(id);
		
		if(op.isPresent()) {
			return op.get();
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
	}
@PostMapping(value="/novo")
	public Cultura inserirCultura(@RequestBody @Valid Cultura cultura ) {
		
		repoC.save(cultura);
		cachingC.removerCache();
		
		return cultura;
	}

@DeleteMapping(value="remover/{id}")
	public Cultura deletarCultura(@PathVariable @Valid Integer id) {
		
		Optional<Cultura> op = repoC.findById(id);
		
		
		if(op.isPresent()) {
			
			repoC.delete(op.get());
			cachingC.removerCache();
			
			return op.get();
			
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
	}

@PutMapping(value="atualizar/{id}")
	public Cultura atualizarCultura(@PathVariable Integer id,@RequestBody @Valid Cultura cultura) {
		
		Optional<Cultura> op = repoC.findById(id);
		
		if(op.isPresent()) {
			
			Cultura culturaBanco = op.get();
			culturaBanco.transferirCultura(cultura);
			repoC.save(culturaBanco);
			cachingC.removerCache();
			return culturaBanco;
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
	}





}
