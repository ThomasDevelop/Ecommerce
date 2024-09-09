package com.ecommerce.Ecommerce.dao.impl;

import com.ecommerce.Ecommerce.config.Conexao;
import com.ecommerce.Ecommerce.dao.IPessoaDAO;
import com.ecommerce.Ecommerce.dto.PessoaDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAOImpl implements IPessoaDAO {
    @Override
    public List<PessoaDTO> listarPessoas() throws SQLException {
        List<PessoaDTO> listaPessoas = new ArrayList<>();
        String sql = "SELECT * FROM PESSOA";
        try (Connection connection = Conexao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PessoaDTO pessoa = new PessoaDTO(
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("cpf"),
                        rs.getString("senha")
                );
                listaPessoas.add(pessoa);
            }
        }
        return listaPessoas;
    }

    @Override
    public void adicionarPessoa(PessoaDTO pessoaDTO) throws SQLException {
        String sql = "INSERT INTO PESSOA (nome, email, cpf, senha) VALUES (?, ?, ?, ?)";
        try (Connection connection = Conexao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, pessoaDTO.getNome());
            ps.setString(2, pessoaDTO.getEmail());
            ps.setString(3, pessoaDTO.getCpf());
            ps.setString(4, pessoaDTO.getSenha());
            ps.executeUpdate();
        }
    }
}