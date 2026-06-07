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

import br.com.gs.projeto_agro.dto.BiomaDTO;
import br.com.gs.projeto_agro.model.Bioma;
import br.com.gs.projeto_agro.repository.BiomaRepository;
import br.com.gs.projeto_agro.service.BiomaCachingService;
import br.com.gs.projeto_agro.service.BiomaPaginacaoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value="/bioma")
public class BiomaController {
	
	 @Autowired
	 private BiomaRepository repoB;
	 
	 @Autowired
	 private BiomaPaginacaoService paginacaoB;
	 
	 @Autowired
	 private BiomaCachingService cachingB;

	 @GetMapping("/paginados")
		public ResponseEntity<Page<BiomaDTO>> paginar	(
				@RequestParam(value = "page", defaultValue = "0") Integer page,
					
				@RequestParam(value = "size", defaultValue = "2") Integer size)
					
					 
				{ PageRequest req = PageRequest.of(page, size);
					 
				Page<BiomaDTO> paginados = paginacaoB.paginar(req);
				return ResponseEntity.ok(paginados);
					 
					 }
	 
	 @GetMapping("/substring")
	    public List<Bioma> retornarBiomaPorSubstring(@RequestParam String substring) {
	        return cachingB.findByNome(substring);
	    }
	
	 @GetMapping(value="/todos")
		public List<Bioma>retornarTodosBiomas(){
			return cachingB.findAll();
		}

	@GetMapping(value="/{id}")
		public Bioma retornarBiomaPorId(@PathVariable @Valid Integer id) {
			
			Optional<Bioma> op = cachingB.findById(id);
			
			if(op.isPresent()) {
				return op.get();
			}else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			}
			
		}
	@PostMapping(value="/novo")
		public Bioma inserirBioma(@RequestBody @Valid Bioma bioma ) {
			
			repoB.save(bioma);
			cachingB.removerCache();
			
			return bioma;
		}

	@DeleteMapping(value="remover/{id}")
		public Bioma deletarBioma(@PathVariable @Valid Integer id) {
			
			Optional<Bioma> op = repoB.findById(id);
			
			
			if(op.isPresent()) {
				
				repoB.delete(op.get());
				cachingB.removerCache();
				
				return op.get();
				
			}else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			}
			
		}

	@PutMapping(value="atualizar/{id}")
		public Bioma atualizarBioma(@PathVariable Integer id,@RequestBody @Valid Bioma bioma ) {
			
			Optional<Bioma> op = repoB.findById(id);
			
			if(op.isPresent()) {
				
				Bioma biomaBanco = op.get();
				biomaBanco.transferirBioma(bioma);
				repoB.save(biomaBanco);
				cachingB.removerCache();
				return biomaBanco;
			}else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			}
			
		}




	   
}
