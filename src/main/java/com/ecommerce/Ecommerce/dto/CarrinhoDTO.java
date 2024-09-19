package com.ecommerce.Ecommerce.dto;

public class CarrinhoDTO {
    private String cpf;
    private Integer idProduto;
    private Integer quantidade;
    private Double precoTotal;
    public CarrinhoDTO(String cpf, Integer idProduto, Integer quantidade, Double precoTotal) {
        this.cpf = cpf;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.precoTotal = precoTotal;
    }

    public String getCpf() { return cpf; }

    public void setCpf(String cpf) { this.cpf = cpf; }

    public Integer getIdProduto() { return idProduto; }
    public void setIdProduto(Integer idProduto) { this.idProduto = idProduto; }
    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
    public Double getPrecoTotal() { return precoTotal; }
    public void setPrecoTotal(Double precoTotal) { this.precoTotal = precoTotal; }
}