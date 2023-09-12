package com.loja.mariane.controller;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.loja.mariane.model.LojaModel;
import com.loja.mariane.repository.LojaRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/loja")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class LojaController {

	// Injeção de dependencia
	@Autowired
	private LojaRepository lojaRepository;
	
	// Pesquisa por todos
	@GetMapping
	public ResponseEntity<List<LojaModel>> getAll() {
	    return ResponseEntity.ok(lojaRepository.findAll());
	}
	
	@PostMapping
	public ResponseEntity<LojaModel> create(@Valid @RequestBody LojaModel loja) {
	    return ResponseEntity.status(HttpStatus.CREATED).body(lojaRepository.save(loja));

	}
	
	// Pesquisa por ID
		@GetMapping("/{id}")
		public ResponseEntity<LojaModel> getbyId(@PathVariable Long id) {
			return lojaRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
					.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

		}

	
		// Pesquisa por Descrição
		@GetMapping("/descricao/{descricao}")
		public ResponseEntity<Object> getByDescricao(@PathVariable String descricao) {
			return ResponseEntity.ok(lojaRepository.findAllByDescricaoContainingIgnoreCase(descricao));
		}	
		
		//Update por ID
		@PutMapping("/{id}")
		public ResponseEntity<Object> update(@Valid @RequestBody LojaModel loja) {
			return ResponseEntity.status(HttpStatus.OK).body(lojaRepository.save(loja));
		}
		
		//Apagar por ID
		@ResponseStatus(HttpStatus.NO_CONTENT)
	    @DeleteMapping("/{id}")
	    public void delete(@PathVariable Long id) {
	        Optional<LojaModel> loja = lojaRepository.findById(id);
	        
	        if(loja.isEmpty())
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	        
	        lojaRepository.deleteById(id);
	        
	    }
		
		
}
