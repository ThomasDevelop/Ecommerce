package com.ecommerce.Ecommerce.dao.impl;

import com.ecommerce.Ecommerce.config.Conexao;
import com.ecommerce.Ecommerce.dao.ICarrinhoDAO;
import com.ecommerce.Ecommerce.dto.CarrinhoDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarrinhoDAOImpl implements ICarrinhoDAO {
    @Override
    public void adicionarAoCarrinho(CarrinhoDTO carrinhoDTO) throws SQLException {
        try (Connection connection = Conexao.getConnection()) {
            String verificarProdutoSql = "SELECT quantidade, preco FROM Produto WHERE idProduto = ?";
            PreparedStatement psVerificarProduto = connection.prepareStatement(verificarProdutoSql);
            psVerificarProduto.setInt(1, carrinhoDTO.getIdProduto());
            ResultSet rsProduto = psVerificarProduto.executeQuery();

            if (!rsProduto.next()) {
                System.out.println("Produto com ID " + carrinhoDTO.getIdProduto() + " não encontrado.");
                return;
            }

            String verificarPessoaSql = "SELECT id FROM Pessoa WHERE id = ?";
            PreparedStatement psVerificarPessoa = connection.prepareStatement(verificarPessoaSql);
            psVerificarPessoa.setInt(1, carrinhoDTO.getIdPessoa());
            ResultSet rsPessoa = psVerificarPessoa.executeQuery();

            if (!rsPessoa.next()) {
                System.out.println("Pessoa com ID " + carrinhoDTO.getIdPessoa() + " não encontrada.");
                return;
            }

            int quantidadeDisponivel = rsProduto.getInt("quantidade");
            double preco = rsProduto.getDouble("preco");

            if (quantidadeDisponivel < carrinhoDTO.getQuantidade()) {
                System.out.println("Quantidade insuficiente no estoque.");
                return;
            }

            String sql = "INSERT INTO Carrinho (idpessoa, idproduto, quantidade, preco_total) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, carrinhoDTO.getIdPessoa());
            ps.setInt(2, carrinhoDTO.getIdProduto());
            ps.setInt(3, carrinhoDTO.getQuantidade());
            ps.setDouble(4, carrinhoDTO.getQuantidade() * preco);
            ps.executeUpdate();

            String atualizarEstoqueSql = "UPDATE Produto SET quantidade = quantidade - ? WHERE idProduto = ?";
            PreparedStatement psAtualizar = connection.prepareStatement(atualizarEstoqueSql);
            psAtualizar.setInt(1, carrinhoDTO.getQuantidade());
            psAtualizar.setInt(2, carrinhoDTO.getIdProduto());
            psAtualizar.executeUpdate();

        }
    }
    @Override
    public List<CarrinhoDTO> listarCarrinhoPorPessoa(int idPessoa) throws SQLException {
        List<CarrinhoDTO> carrinho = new ArrayList<>();
        try (Connection connection = Conexao.getConnection()) {
            String sql = "SELECT c.idProduto, c.quantidade, c.preco_total FROM Carrinho c WHERE c.idPessoa = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idPessoa);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                CarrinhoDTO carrinhoDTO = new CarrinhoDTO(
                        idPessoa,
                        rs.getInt("idProduto"),
                        rs.getInt("quantidade"),
                        rs.getDouble("preco_total")
                );
                carrinho.add(carrinhoDTO);
            }
        }
        return carrinho;
    }
    @Override
    public void finalizarCompra(int idPessoa) throws SQLException {
        try (Connection connection = Conexao.getConnection()) {
            String limparCarrinhoSql = "DELETE FROM Carrinho WHERE idPessoa = ?";
            try (PreparedStatement psLimpar = connection.prepareStatement(limparCarrinhoSql)) {
                psLimpar.setInt(1, idPessoa);
                psLimpar.executeUpdate();
            }
        }
    }
    public int verificarUsuario(String email, String senha) throws SQLException {
        try (Connection connection = Conexao.getConnection()) {
            String verificarPessoaSql = "SELECT id FROM Pessoa WHERE email = ? AND senha = ?";
            try (PreparedStatement psVerificar = connection.prepareStatement(verificarPessoaSql)) {
                psVerificar.setString(1, email);
                psVerificar.setString(2, senha);
                ResultSet rsPessoa = psVerificar.executeQuery();

                if (rsPessoa.next()) {
                    return rsPessoa.getInt("id");
                } else {
                    throw new RuntimeException("Email ou senha inválidos.");
                }
            }
        }
    }
}