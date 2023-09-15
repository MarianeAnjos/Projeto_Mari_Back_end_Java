package com.loja.mariane.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loja.mariane.model.LojaModel;



public interface LojaRepository extends JpaRepository<LojaModel, Long> {
	
	// Método para buscar todos os produtos
		List<LojaModel> findAll();
		
		public List<LojaModel> findByDescricaoContainingIgnoreCase(String descricao);
		
		List<LojaModel> findAllByDescricaoContainingIgnoreCase(String descricao);
		
		
		
}
