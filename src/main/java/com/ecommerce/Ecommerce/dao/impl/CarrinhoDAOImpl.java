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
            String verificarEstoqueSql = "SELECT quantidade, preco FROM Produto WHERE idProduto = ?";
            PreparedStatement psVerificar = connection.prepareStatement(verificarEstoqueSql);
            psVerificar.setInt(1, carrinhoDTO.getIdProduto());
            ResultSet rs = psVerificar.executeQuery();

            if (rs.next()) {
                int quantidadeDisponivel = rs.getInt("quantidade");
                double preco = rs.getDouble("preco");
                if (quantidadeDisponivel < carrinhoDTO.getQuantidade()) {
                    throw new RuntimeException("Quantidade insuficiente no estoque.");
                }

                String sql = "INSERT INTO Carrinho (idPessoa, idProduto, quantidade, preco_total) VALUES (?, ?, ?, ?)";
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
            } else {
                throw new RuntimeException("Produto não encontrado.");
            }
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
    public void finalizarCompra(String email, String senha) throws SQLException {
        try (Connection connection = Conexao.getConnection()) {
            String verificarPessoaSql = "SELECT id FROM Pessoa WHERE email = ? AND senha = ?";
            PreparedStatement psVerificar = connection.prepareStatement(verificarPessoaSql);
            psVerificar.setString(1, email);
            psVerificar.setString(2, senha);
            ResultSet rsPessoa = psVerificar.executeQuery();

            if (rsPessoa.next()) {
                int idPessoa = rsPessoa.getInt("id");

                List<CarrinhoDTO> carrinho = listarCarrinhoPorPessoa(idPessoa);
                double valorTotalCompra = carrinho.stream().mapToDouble(CarrinhoDTO::getPrecoTotal).sum();

                if (carrinho.isEmpty()) {
                    throw new RuntimeException("Carrinho está vazio.");
                }

                System.out.println("Itens no carrinho:");
                for (CarrinhoDTO item : carrinho) {
                    System.out.println("\nProduto ID: " + item.getIdProduto() +
                            "\nQuantidade: " + item.getQuantidade() +
                            "\nPreço Total: " + item.getPrecoTotal());
                }
                System.out.println("Valor total da compra: " + valorTotalCompra + "\nCompra realizada com sucesso");

                String limparCarrinhoSql = "DELETE FROM Carrinho WHERE idPessoa = ?";
                PreparedStatement psLimpar = connection.prepareStatement(limparCarrinhoSql);
                psLimpar.setInt(1, idPessoa);
                psLimpar.executeUpdate();
            }
        }
    }
}