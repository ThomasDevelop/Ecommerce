package com.ecommerce.Ecommerce.dto;

public class ProdutoDTO {
    private Integer idProduto;
    private String nome;
    private int quantidade;
    private double preco;
    public ProdutoDTO(Integer idProduto, String nome, int quantidade, double preco){
        this.idProduto = idProduto;
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
    }
    public Integer getIdProduto() {return idProduto;}
    public void setIdProduto(Integer idProduto) {this.idProduto = idProduto;}
    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}
    public int getQuantidade() {return quantidade;}
    public void setQuantidade(int quantidade) {this.quantidade = quantidade;}
    public double getPreco() {return preco;}
    public void setPreco(double preco) {this.preco = preco;}
}