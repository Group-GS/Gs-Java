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

import br.com.gs.projeto_agro.dto.SensorDTO;
import br.com.gs.projeto_agro.model.Sensor;
import br.com.gs.projeto_agro.repository.SensorRepository;
import br.com.gs.projeto_agro.service.SensorCachingService;
import br.com.gs.projeto_agro.service.SensorPaginacaoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;


@RestController
@RequestMapping(value="/sensor")
public class SensorController {

	@Autowired
	private SensorRepository repoS;
	
	@Autowired 
	private SensorPaginacaoService paginacaoS;
	
	@Autowired
	private SensorCachingService cachingS ;
	
	
	@Operation(
            summary = "Listar Sensor paginados",
            description = "Retorna Sensor em formato paginado com base em page e size",
            tags = "Retorno de informações do Sensor"
        )
	@GetMapping("/paginados")
	public ResponseEntity<Page<SensorDTO>> paginar	(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
				
			@RequestParam(value = "size", defaultValue = "2") Integer size)
				
				 
			{ PageRequest req = PageRequest.of(page, size);
				 
			Page<SensorDTO> paginados = paginacaoS.paginar(req);
			return ResponseEntity.ok(paginados);
				 
				 }
	@Operation(
            summary = "Buscar Sensor por substring",
            description = "Busca Sensor filtrando por parte do nome",
            tags = "Retorno de informações do Sensor"
        )
	@GetMapping("/substring")
    public List<Sensor> retornarSensorPorSubstring(@RequestParam String tipo) {
        return cachingS.findByTipo(tipo);
    }
	
	@Operation(
            summary = "Buscar Sensor por Status",
            description = "Retorna lista de Sensores filtrados pelo Status",
            tags = "Retorno de informações do Sensor"
        )
	@GetMapping("/status")
    public List<Sensor> retornarSensorPorStatus(@RequestParam String status) {
        return cachingS.findByStatus(status);
    }
	@Operation(
            summary = "Listar todos os Sensores (cache)",
            description = "Retorna todos os Sensores utilizando cache para performance",
            tags = "Retorno de informações do Sensor"
        )
	@GetMapping(value="/todos")
	public List<Sensor>retornarTodosSensores(){
		return cachingS.findAll();
	}
	@Operation(
            summary = "Buscar Sensor por ID",
            description = "Retorna um Sensor específico pelo ID",
            tags = "Retorno de informações do Sensor"
        )
	@GetMapping(value="/{id}")
		public Sensor retornarSensorPorId(@PathVariable @Valid Integer id) {
			
			Optional<Sensor> op = cachingS.findById(id);
			
			if(op.isPresent()) {
				return op.get();
			}else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			}
			
		}
	@Operation(
            summary = "Criar novo Sensor",
            description = "Insere um novo Sensor no banco e limpa cache",
            tags = "Inserção de informações do Sensor"
        )
	@PostMapping(value="/novo")
		public Sensor inserirSensor(@RequestBody @Valid Sensor sensor) {
			
			repoS.save(sensor);
			cachingS.removerCache();
			
			return sensor;
		}
	
	@Operation(
            summary = "Remover Sensor",
            description = "Remove um Sensor pelo ID e limpa cache",
            tags = "Remoção de informações do Sensor"
        )
	@DeleteMapping(value="remover/{id}")
		public Sensor deletarSensor(@PathVariable @Valid Integer id) {
			
			Optional<Sensor> op = repoS.findById(id);
			
			
			if(op.isPresent()) {
				
				repoS.delete(op.get());
				cachingS.removerCache();

				
				return op.get();
				
			}else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			}
			
		}

	@Operation(
            summary = "Atualizar Sensor",
            description = "Atualiza dados de um Sensor existente",
            tags = "Atualização dos dados do Sensor"
        )
	@PutMapping(value="atualizar/{id}")
		public Sensor atualizarSensor(@PathVariable Integer id,@RequestBody @Valid Sensor sensor) {
			
			Optional<Sensor> op = repoS.findById(id);
			
			if(op.isPresent()) {
				
				Sensor sensorBanco = op.get();
				sensorBanco.transferirSensor(sensor);
				repoS.save(sensorBanco);
				cachingS.removerCache();
				return sensorBanco;
			}else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			}
			
		}
		
	}




	
	


