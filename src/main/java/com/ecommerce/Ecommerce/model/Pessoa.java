package com.ecommerce.Ecommerce.model;

public class Pessoa {
    private Integer idPessoa;
    private String nome;
    private String email;
    private String cpf;
    private String senha;
    public Pessoa(Integer idPessoa, String nome, String email, String cpf, String senha) {
        this.idPessoa = idPessoa;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.senha = senha;
    }
    public Integer getIdPessoa() {
        return idPessoa;
    }
    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail(){ return email;}
    public void setEmail(String email) {
        this.email = email;
    }
    public String getCpf(){ return cpf;}
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getSenha(){ return senha;}
    public void setSenha(String senha) {
        this.senha = senha;
    }
}