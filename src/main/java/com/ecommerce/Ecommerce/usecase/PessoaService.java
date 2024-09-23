package com.ecommerce.Ecommerce.usecase;

import com.ecommerce.Ecommerce.dao.impl.PessoaDAOImpl;
import com.ecommerce.Ecommerce.dto.PessoaDTO;
import com.ecommerce.Ecommerce.util.MensagensConstanteUtils;

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
                System.out.println(MensagensConstanteUtils.NENHUM_PESSOA_ENCONTRADA);
            }
        } catch (SQLException e) {
            System.out.println(MensagensConstanteUtils.ERRO_AO_LISTAR_PESSOA);
        }
    }

    public void adicionarPessoa(String nome, String email, String cpf, String senha) {
        PessoaDTO pessoaDTO = new PessoaDTO(nome, email, cpf, senha);
        try {
            if (pessoaDAOImpl.emailExiste(email)) {
                System.out.println(MensagensConstanteUtils.ERRO_O_EMAIL_JA_EXISTE);
                return;
            }
            if (pessoaDAOImpl.cpfExiste(cpf)) {
                System.out.println(MensagensConstanteUtils.ERRO_O_CPF_JA_EXISTE);
                return;
            }
            pessoaDAOImpl.adicionarPessoa(pessoaDTO);
        } catch (SQLException e) {
            System.out.println(MensagensConstanteUtils.ERRO_AO_ADICIONAR_PESSOA);
        }
    }

    public boolean emailExiste(String email) {
        try {
            return pessoaDAOImpl.emailExiste(email);
        } catch (SQLException e) {
            System.out.println(MensagensConstanteUtils.ERRO_AO_VERIFICAR_EMAIL_PESSOA);
        }
        return false;
    }

    public boolean cpfExiste(String cpf) {
        try {
            return pessoaDAOImpl.cpfExiste(cpf);
        } catch (SQLException e) {
            System.out.println(MensagensConstanteUtils.ERRO_AO_VERIFICAR_CPF_PESSOA);
        }
        return false;
    }
}