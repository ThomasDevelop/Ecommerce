package com.ecommerce.Ecommerce.dto;

public class PessoaDTO {
    private String nome;
    private String email;
    private String cpf;
    private String senha;
    public PessoaDTO(String nome, String email, String cpf, String senha){
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.senha = senha;
    }
    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}
    public String getEmail() {return email;}
    public void setEmail() {this.email = email;}
    public String getCpf() {return cpf;}
    public void setCpf() {this.cpf = cpf;}
    public String getSenha() {return senha;}
    public void setSenha() {this.senha = senha;}
}