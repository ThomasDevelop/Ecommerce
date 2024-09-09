package com.ecommerce.Ecommerce.dao.impl;

import com.ecommerce.Ecommerce.config.Conexao;
import com.ecommerce.Ecommerce.dao.IProdutoDAO;
import com.ecommerce.Ecommerce.dto.ProdutoDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAOImpl implements IProdutoDAO {
    @Override
    public List<ProdutoDTO> listarProduto() {
        List<ProdutoDTO> produtos = new ArrayList<>();;
        try (Connection connection = Conexao.getConnection()) {
            String sql = "SELECT * FROM produto";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ProdutoDTO produtoDTO = new ProdutoDTO(
                        rs.getInt("idProduto"),
                        rs.getString("nome"),
                        rs.getInt("quantidade"),
                        rs.getDouble("preco"));
                produtos.add(produtoDTO);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return produtos;
    }
    @Override
    public void adicionarProduto(ProdutoDTO produtoDTO) {
        try (Connection connection = Conexao.getConnection()){
        String sql = "INSERT INTO Produto (nome, quantidade, preco) VALUES (?, ?, ?)";
         PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, produtoDTO.getNome());
            ps.setInt(2, produtoDTO.getQuantidade());
            ps.setDouble(3, produtoDTO.getPreco());
            ps.executeUpdate();
        } catch (SQLException e) {

            throw new RuntimeException(e.getMessage());
        }
    }
}