package com.loja.mariane.model;

public class ProdutoLojaRequest {
    private Long idproduto;
    private Long idloja;
    private Float custo;
    
	public Long getIdproduto() {
		return idproduto;
	}
	public void setIdproduto(Long idproduto) {
		this.idproduto = idproduto;
	}
	public Long getIdloja() {
		return idloja;
	}
	public void setIdloja(Long idloja) {
		this.idloja = idloja;
	}
	public Float getCusto() {
		return custo;
	}
	public void setCusto(Float custo) {
		this.custo = custo;
	}

}
