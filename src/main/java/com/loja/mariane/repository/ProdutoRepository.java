package com.loja.mariane.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.loja.mariane.model.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	// Método para buscar todos os produtos
	List<Produto> findAll();

	public Optional<Produto> findByDescricao(String descicao);

	Object findAllByDescricaoContainingIgnoreCase(String descricao);

	/*
	 * Optional<Produto> findByCusto(Float custo);
	 * 
	 * Object findAllByCustoContainingIgnoreCase(Float custo);
	 */
}
