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

import br.com.gs.projeto_agro.dto.LocalDTO;
import br.com.gs.projeto_agro.model.Local;
import br.com.gs.projeto_agro.repository.LocalRepository;
import br.com.gs.projeto_agro.service.LocalCachingService;
import br.com.gs.projeto_agro.service.LocalPaginacaoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value="/local")
public class LocalController {

	@Autowired
	private LocalRepository repoL;
	
	@Autowired
	private LocalPaginacaoService paginacaoL;
	
	@Autowired
	private LocalCachingService cachingL;

@GetMapping("/paginados")
	public ResponseEntity<Page<LocalDTO>> paginar	(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
				
			@RequestParam(value = "size", defaultValue = "2") Integer size)
				
				 
			{ PageRequest req = PageRequest.of(page, size);
				 
			Page<LocalDTO> paginados = paginacaoL.paginar(req);
			return ResponseEntity.ok(paginados);
				 
				 }
@GetMapping("/substring")
public List<Local> substring(@RequestParam String substring) {
    return cachingL.findByNome(substring);
}
	
@GetMapping("/local")
public List<Local> retornarSensorPorBioma(@RequestParam Integer idBioma) {
    return cachingL.findByBioma(idBioma);
}
	
@GetMapping(value="/todos")
	public List<Local>retornarTodosLocais(){
		return cachingL.findAll();
	}

@GetMapping(value="/{id}")
	public Local retornarLocalPorId(@PathVariable @Valid Integer id) {
		
		Optional<Local> op = repoL.findById(id);
		
		if(op.isPresent()) {
			return op.get();
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
	}
@PostMapping(value="/novo")
	public Local inserirLocal(@RequestBody @Valid Local local ) {
		
		repoL.save(local);
		cachingL.removerCache();
		
		return local;
	}

@DeleteMapping(value="remover/{id}")
	public Local deletarLocal(@PathVariable @Valid Integer id) {
		
		Optional<Local> op = cachingL.findById(id);
		
		
		if(op.isPresent()) {
			
			repoL.delete(op.get());
			cachingL.removerCache();
			
			return op.get();
			
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
	}

@PutMapping(value="atualizar/{id}")
	public Local atualizarLocal(@PathVariable Integer id,@RequestBody @Valid Local local ) {
		
		Optional<Local> op = repoL.findById(id);
		
		if(op.isPresent()) {
			
			Local localBanco = op.get();
			localBanco.transferirLocal(local);
			repoL.save(localBanco);
			cachingL.removerCache();
			return localBanco;
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
	}





	





	
}
