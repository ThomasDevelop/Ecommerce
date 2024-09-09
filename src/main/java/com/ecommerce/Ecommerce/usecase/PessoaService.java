package com.ecommerce.Ecommerce.usecase;

import com.ecommerce.Ecommerce.dao.impl.PessoaDAOImpl;
import com.ecommerce.Ecommerce.dto.PessoaDTO;

import java.sql.SQLException;
import java.util.List;

public class PessoaService {
    private static PessoaDAOImpl pessoaDAOImpl = new PessoaDAOImpl();

    public static void listarPessoa() throws SQLException {
        List<PessoaDTO> pessoas = pessoaDAOImpl.listarPessoas();
        if (pessoas != null && !pessoas.isEmpty()) {
            for (PessoaDTO p : pessoas) {
                System.out.println("-------------------\nLista de Produto");
                System.out.println("\nNome: " + p.getNome() +
                        "\nCpf: " + p.getCpf() + "\nEmail: " + p.getEmail());
                System.out.println("-------------------");
            }
        } else {
            System.out.println("Nenhum produto encontrado.");
        }
    }
    public void adicionarPessoa(String nome, String email, String cpf, String senha) {
        PessoaDTO pessoaDTO = new PessoaDTO(nome, email, cpf, senha);
        try {
            pessoaDAOImpl.adicionarPessoa(pessoaDTO);
        } catch (Exception e) {
            System.out.println("Erro ao adicionar produto: " + e.getMessage());
        }
    }
}