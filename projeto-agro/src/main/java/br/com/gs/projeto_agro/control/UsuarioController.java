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

import br.com.gs.projeto_agro.dto.UsuarioDTO;
import br.com.gs.projeto_agro.model.Usuario;
import br.com.gs.projeto_agro.repository.UsuarioRepository;
import br.com.gs.projeto_agro.service.UsuarioCachingService;
import br.com.gs.projeto_agro.service.UsuarioPaginacaoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value="/usuario")
public class UsuarioController {

	
	@Autowired
	private UsuarioRepository repoU;
	
	@Autowired 
	private UsuarioPaginacaoService paginacaoU; 
	
	@Autowired 
	private UsuarioCachingService cachingU;
	
	@Operation(
            summary = "Listar Usuario paginados",
            description = "Retorna Usuario em formato paginado com base em page e size",
            tags = "Retorno de informações do Usuario"
        )
@GetMapping("/paginados")
public ResponseEntity<Page<UsuarioDTO>> paginar	(
		@RequestParam(value = "page", defaultValue = "0") Integer page,
			
		@RequestParam(value = "size", defaultValue = "2") Integer size)
			
			 
		{ PageRequest req = PageRequest.of(page, size);
			 
		Page<UsuarioDTO> paginados = paginacaoU.paginar(req);
		return ResponseEntity.ok(paginados);
			 
			 }


	@Operation(
            summary = "Buscar Usuario por substring",
            description = "Busca Usuario  por parte do nome",
            tags = "Retorno de informações do Usuario"
        )
@GetMapping("/substring")
public List<Usuario> retornarUsuarioPorSubstring(@RequestParam String substring){
	return cachingU.findByNome(substring); 
};

@Operation(
        summary = "Buscar Usuario por Usuario",
        description = "Retorna lista de Usuarios filtrados pelo email",
        tags = "Retorno de informações do Usuario"
    )	
@GetMapping("/por_email")
public Usuario retornarUsuarioPorEmail(@RequestParam String email) {
	
	Optional<Usuario> op = cachingU.findByEmail(email);
	
	if (op.isPresent()) {
		return op.get();
	}else {
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	}

@Operation(
        summary = "Listar todos os Usuarios (cache)",
        description = "Retorna todos os Usuarios utilizando cache para performance",
        tags = "Retorno de informações do Usuario"
    )	
@GetMapping(value="/todos")
	public List<Usuario>retornarTodosUsuarios(){
		return cachingU.findAll();
	}

@Operation(
        summary = "Buscar Usuario por ID",
        description = "Retorna um Usuario específico pelo ID",
        tags = "Retorno de informações do Usuario"
    )
@GetMapping(value="/{id}")
	public Usuario retornarUsuarioPorId(@PathVariable @Valid Integer id) {
		
		Optional<Usuario> op = cachingU.findById(id);
		
		if(op.isPresent()) {
			return op.get();
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
	}

@Operation(
        summary = "Criar novo Usuario",
        description = "Insere um novo Usuario no banco e limpa cache",
        tags = "Inserção de informações do Usuario"
    )
@PostMapping(value="/novo")
	public Usuario inserirUsuario(@RequestBody @Valid Usuario usuario) {
		
		repoU.save(usuario);
		cachingU.removerCache();
		
		
		return usuario;
	}

@Operation(
        summary = "Remover Usuario",
        description = "Remove um usuario pelo ID e limpa cache",
        tags = "Remoção de informações do usuario"
    )
@DeleteMapping(value="remover/{id}")
	public Usuario deletarUsuario(@PathVariable @Valid Integer id) {
		
		Optional<Usuario> op = repoU.findById(id);
		
		
		if(op.isPresent()) {
			
			repoU.delete(op.get());
			cachingU.removerCache();
			
			return op.get();
			
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
	}

@Operation(
        summary = "Atualizar Usuario",
        description = "Atualiza dados de um Usuario existente",
        tags = "Atualização dos dados do Usuario"
    )
@PutMapping(value="atualizar/{id}")
	public Usuario atualizarPet(@PathVariable Integer id,@RequestBody @Valid Usuario usuario) {
		
		Optional<Usuario> op = repoU.findById(id);
		
		if(op.isPresent()) {
			
			Usuario usuarioBanco = op.get();
			usuarioBanco.transferirUsuario(usuario);
			repoU.save(usuarioBanco);
			cachingU.removerCache();
			return usuarioBanco;
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
	}
	
}
