package com.ecommerce.Ecommerce.usecase;

import com.ecommerce.Ecommerce.dao.impl.PessoaDAOImpl;
import com.ecommerce.Ecommerce.dto.PessoaDTO;

import java.sql.SQLException;
import java.util.List;

public class PessoaService {
    private static PessoaDAOImpl pessoaDAOImpl = new PessoaDAOImpl();

    public static void listarPessoa() {
        try {
            List<PessoaDTO> pessoas = pessoaDAOImpl.listarPessoas();
            if (pessoas != null && !pessoas.isEmpty()) {
                for (PessoaDTO p : pessoas) {
                    System.out.println("-------------------\nLista de Pessoas");
                    System.out.println("\nNome: " + p.getNome() +
                            "\nCpf: " + p.getCpf() + "\nEmail: " + p.getEmail());
                    System.out.println("-------------------");
                }
            } else {
                System.out.println("Nenhuma pessoa encontrada.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar pessoa: " + e.getMessage());
        }
    }

    public void adicionarPessoa(String nome, String email, String cpf, String senha) {
        PessoaDTO pessoaDTO = new PessoaDTO(nome, email, cpf, senha);
        try {
            if (pessoaDAOImpl.emailExiste(email)) {
                System.out.println("Erro: O email já está cadastrado.");
                return;
            }
            if (pessoaDAOImpl.cpfExiste(cpf)) {
                System.out.println("Erro: O CPF já está cadastrado.");
                return;
            }

            pessoaDAOImpl.adicionarPessoa(pessoaDTO);

        } catch (SQLException e) {
            System.out.println("Erro ao adicionar pessoa: " + e.getMessage());
        }
    }

    public boolean emailExiste(String email) {
        try {
            return pessoaDAOImpl.emailExiste(email);
        } catch (SQLException e) {
            System.out.println("Erro ao verificar o email: " + e.getMessage());
        }
        return false;
    }

    public boolean cpfExiste(String cpf) {
        try {
            return pessoaDAOImpl.cpfExiste(cpf);
        } catch (SQLException e) {
            System.out.println("Erro ao verificar o CPF: " + e.getMessage());
        }
        return false;
    }
}