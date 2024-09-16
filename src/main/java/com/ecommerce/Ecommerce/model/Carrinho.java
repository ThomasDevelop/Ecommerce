package com.ecommerce.Ecommerce.model;

public class Carrinho {
    private Integer id;
    private Integer idPessoa;
    private Integer idProduto;
    private Integer quantidade;
    private Double precoTotal;

    public Carrinho(Integer id, Integer idPessoa, Integer idProduto, Integer quantidade, Double precoTotal) {
        this.id = id;
        this.idPessoa = idPessoa;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.precoTotal = precoTotal;
    }
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getIdPessoa() { return idPessoa; }
    public void setIdPessoa(Integer idPessoa) { this.idPessoa = idPessoa; }

    public Integer getIdProduto() { return idProduto; }
    public void setIdProduto(Integer idProduto) { this.idProduto = idProduto; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public Double getPrecoTotal() { return precoTotal; }
    public void setPrecoTotal(Double precoTotal) { this.precoTotal = precoTotal; }
}