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

import br.com.gs.projeto_agro.dto.CultivoDTO;
import br.com.gs.projeto_agro.model.Cultivo;
import br.com.gs.projeto_agro.repository.CultivoRepository;
import br.com.gs.projeto_agro.service.CultivoCachingService;
import br.com.gs.projeto_agro.service.CultivoPaginacaoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value="/cultivo")
public class CultivoController {

	@Autowired
	private CultivoRepository repoC;
	
	@Autowired
	private CultivoPaginacaoService paginacaoC;
	
	@Autowired
	private CultivoCachingService cachingC;

@GetMapping("/paginados")
	public ResponseEntity<Page<CultivoDTO>> paginar	(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
				
			@RequestParam(value = "size", defaultValue = "2") Integer size)
				
				 
			{ PageRequest req = PageRequest.of(page, size);
				 
			Page<CultivoDTO> paginados = paginacaoC.paginar(req);
			return ResponseEntity.ok(paginados);
				 
				 }


@GetMapping("/substring")
	    public List<Cultivo> retornarCultivoPorStatus(@RequestParam String substring) {
	        return cachingC.findByStatusSubstring(substring);
	    }
@GetMapping("/por_local")
	    public List<Cultivo> retornarCultivoPorLocal(@RequestParam Integer localId) {
	        return cachingC.findByLocal(localId);
	    }
@GetMapping("/por_cultura")
	    public List<Cultivo> retornarCultivoPorCultura(@RequestParam Integer culturaId) {
	        return cachingC.findByCultura(culturaId);
	    }
	
@GetMapping(value="/todos")
	public List<Cultivo>retornarTodos(){
		return cachingC.findAll();
	}

@GetMapping(value="/{id}")
	public Cultivo retornarCultivoPorId(@PathVariable @Valid Integer id) {
		
		Optional<Cultivo> op = cachingC.findById(id);
		
		if(op.isPresent()) {
			return op.get();
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
	}
@PostMapping(value="/novo")
	public Cultivo inserirCultivo(@RequestBody @Valid Cultivo cultivo ) {
		
		repoC.save(cultivo);
		cachingC.removerCache();
		
		return cultivo;
	}

@DeleteMapping(value="remover/{id}")
	public Cultivo deletarCultivo(@PathVariable @Valid Integer id) {
		
		Optional<Cultivo> op = repoC.findById(id);
		
		
		if(op.isPresent()) {
			
			repoC.delete(op.get());
			cachingC.removerCache();
			
			return op.get();
			
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
	}

@PutMapping(value="atualizar/{id}")
	public Cultivo atualizarCultivo(@PathVariable Integer id,@RequestBody @Valid Cultivo cultivo ) {
		
		Optional<Cultivo> op = repoC.findById(id);
		
		if(op.isPresent()) {
			
			Cultivo cultivoBanco = op.get();
			cultivoBanco.transferirCultivo(cultivo);
			repoC.save(cultivoBanco);
			cachingC.removerCache();
			return cultivoBanco;
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
	}






}
