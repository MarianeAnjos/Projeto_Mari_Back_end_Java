package com.loja.mariane.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "produtoloja")
public class ProdutoLojaModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "idproduto", referencedColumnName = "id")
	private ProdutoModel produto;

	@ManyToOne
	@JoinColumn(name = "idloja", referencedColumnName = "id")
	private LojaModel loja;
	
	private float custo;

	public ProdutoLojaModel() {
	}

	public ProdutoLojaModel(ProdutoModel produto, LojaModel loja, float custo) {
		this.produto = produto;
		this.loja = loja;
		this.custo = custo;
	}
	
}
