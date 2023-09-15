package com.loja.mariane.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loja.mariane.model.LojaModel;
import com.loja.mariane.model.ProdutoLojaModel;
import com.loja.mariane.model.ProdutoModel;
import com.loja.mariane.repository.LojaRepository;
import com.loja.mariane.repository.ProdutoLojaRepository;
import com.loja.mariane.repository.ProdutoRepository;
import com.loja.mariane.resquest.ProdutoLojaRequest;



@RestController
@RequestMapping("/produtoLoja")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class ProdutoLojaController {

	// Injeção de dependencia produto loja
	@Autowired
	private ProdutoLojaRepository produtoLojaRepository;

	// Injeção de dependencia produto
	@Autowired
	private ProdutoRepository produtoRepository;

	// Injeção de dependencia loja
	@Autowired
	private LojaRepository lojaRepository;

	// Pesquisa por todos
	@GetMapping
	public ResponseEntity<List<ProdutoLojaModel>> getAll() {
		return ResponseEntity.ok(produtoLojaRepository.findAll());
	}

	// Criando novo produto loja
	@PostMapping
	public ResponseEntity<?> create(@RequestBody ProdutoLojaRequest request) {
	    try {
	        ProdutoModel produto = produtoRepository.getReferenceById(request.getIdproduto());
	        LojaModel loja = lojaRepository.getReferenceById(request.getIdloja());
	        ProdutoLojaModel produtoLoja = new ProdutoLojaModel(produto, loja, request.getCusto());
	        ProdutoLojaModel savedProdutoLoja = produtoLojaRepository.save(produtoLoja);
	        return ResponseEntity.status(HttpStatus.CREATED).body(savedProdutoLoja);
	    } catch (NumberFormatException e) {
	        return ResponseEntity.badRequest().body("Erro de formato nos parâmetros.");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro interno.");
	    }
	}


	// Pesquisa por ID
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoModel> getbyId(@PathVariable Long id) {
		return produtoRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

	}

}
