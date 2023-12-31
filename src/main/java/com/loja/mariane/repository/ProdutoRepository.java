package com.loja.mariane.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loja.mariane.model.ProdutoModel;

public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {

	// Método para buscar todos os produtos
	List<ProdutoModel> findAll();

	public Optional<ProdutoModel> findByDescricao(String descicao);


	Object findAllByDescricaoContainingIgnoreCase(String descricao);

	/*
	 * Optional<Produto> findByCusto(Float custo);
	 * 
	 * Object findAllByCustoContainingIgnoreCase(Float custo);
	 */
}
