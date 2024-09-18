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
        if (emailExiste(pessoaDTO.getEmail())) {
            throw new SQLException("Erro ao adicionar pessoa: O email já existe.");
        }
        if (cpfExiste(pessoaDTO.getCpf())) {
            throw new SQLException("Erro ao adicionar pessoa: O CPF já existe.");
        }

        String sql = "INSERT INTO PESSOA VALUES (?, ?, ?, ?)";
        try (Connection connection = Conexao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, pessoaDTO.getNome());
            ps.setString(2, pessoaDTO.getEmail());
            ps.setString(3, pessoaDTO.getCpf());
            ps.setString(4, pessoaDTO.getSenha());
            ps.executeUpdate();
        }
    }

    public boolean emailExiste(String email) throws SQLException {
        String sql = "SELECT COUNT(*) FROM PESSOA WHERE email = ?";
        try (Connection connection = Conexao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    public boolean cpfExiste(String cpf) throws SQLException {
        String sql = "SELECT COUNT(*) FROM PESSOA WHERE cpf = ?";
        try (Connection connection = Conexao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, cpf);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }
}