package com.example.demo.controller;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PersonaDTO;
import com.example.demo.services.PersonaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//@Author Juan José Vargas
//@Controller ya no es necesaria porque está @RestController
@RestController
//Si el origin es *, se puede acceder desde cualquier parte del mundo, pero hay que observar que métodos acepta. Estos son los que se colocan con el Request...
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
@RequestMapping(path = "/api/v1/persona")
@Api(value="API REST MUTANT")
public class PersonaController {
	private PersonaService service;
//En este caso la instancia se administra automaticamente por medio de la etiqueta RestController
	public PersonaController(PersonaService service) {
		super();
		this.service = service;
	}

	@ApiOperation(value="Retorna todos los mutantes")
	@GetMapping("/")

@Transactional
	public ResponseEntity getAll(){
		
		try {
			
			
			
			return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
			
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body
					("{\"message\": \"Error. Registros NO encontrados.\"}");
			
		}
		
		
		
		
	}
	
	

/*
 	@ApiOperation(value="")
	@Transactional
	public ResponseEntity post(@RequestBody PersonaDTO dto) {
		
		try {
			

			return ResponseEntity.status(HttpStatus.OK).body(service.save(dto));
			
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body
		("{\"message\": \"Error. Please check the BODY request, and try again later.\"}");
						
		}
		
	}
	
	*/	
	
	
	
		@ApiOperation(value="Retorna un mutante")
	@GetMapping("/{id}")
//	@CrossOrigin(origins = "*")
	@Transactional
	public ResponseEntity getOne(@PathVariable int id) {
		
		try {
			
			return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
			
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body
					("{\"message\": \"Error. \"}");
			
		}
		

		
	}	
	
	
	
	
	
	@ApiOperation(value="Elimina un mutante ")
	@DeleteMapping("/{id}")

	@Transactional
	public ResponseEntity delete(@PathVariable int id) {
		
		try {
	System.out.println("Se recibio id:" +id);
			service.delete(id,false);
			return ResponseEntity.status(HttpStatus.OK).body("{\"message\": \"Registro eliminado\"}");
			
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body
	("{\"message\": \"Error. Please check the ID or BODY request, and try again later.\"}");
						
		}
		
	}
		
	@ApiOperation(value="Edita un mutante")
@PutMapping("/{id}")

@Transactional
public ResponseEntity put(@PathVariable int id, @RequestBody PersonaDTO dto) {
	
	try {
		
		if(service.isMutant(dto.getAdn())) {
			return ResponseEntity.status(HttpStatus.OK).body(service.update(id, dto));
		}else {
		service.delete(id, true);
			
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body
					("{\"message\": \"Ya no es mutante. Registro eliminado\"}");
		}
	
		
	} catch (Exception e) {
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body
				("{\"message\": \"Error. Please check the ID or BODY request, and try again later.\"}");
					
	}
	
}
	@ApiOperation(value="Comprueba si es mutante")
	@PostMapping("/mutant/")
@Transactional
public ResponseEntity isMutant(@RequestBody PersonaDTO dto) {
		
		try {

			if(service.isMutant(dto.getAdn())) {
				service.contar(false);
				return ResponseEntity.status(HttpStatus.OK).body(service.save(dto));
			}
	
			
			else {
				service.contar(true);
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("{\"message\": \"Not mutant\"}");
			}
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body
		("{\"message\": \"Error. Please check the BODY request, and try again later.\"}");
						
		}
		
	}
	@ApiOperation(value="Retorna el ratio")
	@GetMapping("/stats/")
@Transactional
public ResponseEntity stats() {
		
		try {
//int human=service.cantidadDeHumanos();

		 return ResponseEntity.status(HttpStatus.OK).body(service.getStats());
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body
		("{\"message\": \"Error. Please check the BODY request, and try again later.\"}");
						
		}
		
	}
}

	
