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
import io.swagger.v3.oas.annotations.Operation;
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

	
	@Operation(
            summary = "Listar Local paginados",
            description = "Retorna Local em formato paginado com base em page e size",
            tags = "Retorno de informações do Local"
        )
@GetMapping("/paginados")
	public ResponseEntity<Page<LocalDTO>> paginar	(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
				
			@RequestParam(value = "size", defaultValue = "2") Integer size)
				
				 
			{ PageRequest req = PageRequest.of(page, size);
				 
			Page<LocalDTO> paginados = paginacaoL.paginar(req);
			return ResponseEntity.ok(paginados);
				 
				 }
	@Operation(
            summary = "Buscar Local por substring",
            description = "Busca Local filtrando por parte do nome",
            tags = "Retorno de informações do Local"
        )
@GetMapping("/substring")
public List<Local> retornarLocalPorSubstring(@RequestParam String substring) {
    return cachingL.findByNome(substring);
}
	@Operation(
            summary = "Buscar Local por Bioma",
            description = "Retorna lista de Local filtrados pelo Bioma",
            tags = "Retorno de informações do Local"
        )
@GetMapping("/bioma")
public List<Local> retornarSensorPorBioma(@RequestParam Integer idBioma) {
    return cachingL.findByBioma(idBioma);
}
	@Operation(
            summary = "Listar todos os Locais (cache)",
            description = "Retorna todos os Locais utilizando cache para performance",
            tags = "Retorno de informações do Local"
        )
@GetMapping(value="/todos")
	public List<Local>retornarTodosLocais(){
		return cachingL.findAll();
	}
	@Operation(
            summary = "Buscar Local por ID",
            description = "Retorna um Local específico pelo ID",
            tags = "Retorno de informações do Local"
        )
@GetMapping(value="/{id}")
	public Local retornarLocalPorId(@PathVariable @Valid Integer id) {
		
		Optional<Local> op = repoL.findById(id);
		
		if(op.isPresent()) {
			return op.get();
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
	}
	@Operation(
            summary = "Criar novo Local",
            description = "Insere um novo Local no banco e limpa cache",
            tags = "Inserção de informações do Local"
        )
@PostMapping(value="/novo")
	public Local inserirLocal(@RequestBody @Valid Local local ) {
		
		repoL.save(local);
		cachingL.removerCache();
		
		return local;
	}
	@Operation(
            summary = "Remover Local",
            description = "Remove um Local pelo ID e limpa cache",
            tags = "Remoção de informações do Local"
        )
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
	@Operation(
            summary = "Atualizar Local",
            description = "Atualiza dados de um Local existente",
            tags = "Atualização dos dados do Local"
        )
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
