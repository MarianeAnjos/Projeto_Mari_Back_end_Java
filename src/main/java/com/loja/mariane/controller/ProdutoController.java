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

import com.loja.mariane.model.ProdutoModel;
import com.loja.mariane.repository.ProdutoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produto")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class ProdutoController {

	// Injeção de dependencia
	@Autowired
	private ProdutoRepository produtoRepository;

	// Pesquisa por todos
	@GetMapping
	public ResponseEntity<List<ProdutoModel>> getAll() {
		return ResponseEntity.ok(produtoRepository.findAll());
	}

	// Criando novo produto
	@PostMapping
	public ResponseEntity<ProdutoModel> create(@Valid @RequestBody ProdutoModel produto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));

	}

	// Pesquisa por ID
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoModel> getbyId(@PathVariable Long id) {
		return produtoRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

	}

	/*
	 * // Pesquisa por Custo
	 * 
	 * @GetMapping("/custo/{custo}") public ResponseEntity<Object>
	 * getByCusto(@PathVariable String custo) { return
	 * ResponseEntity.ok(produtoRepository.findAllByCustoContainingIgnoreCase(custo)
	 * ); }
	 */

	// Pesquisa por Descrição
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<Object> getByDescricao(@PathVariable String descricao) {
		return ResponseEntity.ok(produtoRepository.findAllByDescricaoContainingIgnoreCase(descricao));
	}

	// Update por ID
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@Valid @RequestBody ProdutoModel produto) {
		return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produto));
	}

	// Apagar por ID
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<ProdutoModel> produto = produtoRepository.findById(id);

		if (produto.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);

		produtoRepository.deleteById(id);

	}

}
