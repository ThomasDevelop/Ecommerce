package com.ecommerce.Ecommerce.dao.impl;

import com.ecommerce.Ecommerce.config.Conexao;
import com.ecommerce.Ecommerce.dao.ICarrinhoDAO;
import com.ecommerce.Ecommerce.dto.CarrinhoDTO;
import com.ecommerce.Ecommerce.util.MensagensConstanteUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarrinhoDAOImpl implements ICarrinhoDAO {
    @Override
    public void adicionarAoCarrinho(CarrinhoDTO carrinhoDTO) throws SQLException {
        String verificarProdutoSql = "SELECT quantidade, preco FROM Produto WHERE idProduto = ?";

        try (Connection connection = Conexao.getConnection()) {

            PreparedStatement psVerificarProduto = connection.prepareStatement(verificarProdutoSql);

            psVerificarProduto.setInt(1, carrinhoDTO.getIdProduto());
            ResultSet rsProduto = psVerificarProduto.executeQuery();

            if (!rsProduto.next()) {
                System.out.println(MensagensConstanteUtils.PRODUTO_NAO_ENCONTRADO_DAO);
                return;
            }

            String verificarPessoaSql = "SELECT cpf FROM Pessoa WHERE cpf = ?";
            PreparedStatement psVerificarPessoa = connection.prepareStatement(verificarPessoaSql);
            psVerificarPessoa.setString(1, carrinhoDTO.getCpf());
            ResultSet rsPessoa = psVerificarPessoa.executeQuery();

            if (!rsPessoa.next()) {
                System.out.println(MensagensConstanteUtils.USUARIO_COM_CPF + carrinhoDTO.getCpf() + MensagensConstanteUtils.NAO_ENCONTRADO_COM_CPF);
                return;
            }

            int quantidadeDisponivel = rsProduto.getInt("quantidade");
            double preco = rsProduto.getDouble("preco");

            if (quantidadeDisponivel < carrinhoDTO.getQuantidade()) {
                System.out.println(MensagensConstanteUtils.QUANTIDADE_INSUFICIENTE_NO_ESTOQUE);
                return;
            }

            String sql = "INSERT INTO Carrinho (cpf, idProduto, quantidade, preco_total) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, carrinhoDTO.getCpf());
            ps.setInt(2, carrinhoDTO.getIdProduto());
            ps.setInt(3, carrinhoDTO.getQuantidade());
            ps.setDouble(4, carrinhoDTO.getQuantidade() * preco);
            ps.executeUpdate();

            String atualizarEstoqueSql = "UPDATE Produto SET quantidade = quantidade - ? WHERE idProduto = ?";
            PreparedStatement psAtualizar = connection.prepareStatement(atualizarEstoqueSql);
            psAtualizar.setInt(1, carrinhoDTO.getQuantidade());
            psAtualizar.setInt(2, carrinhoDTO.getIdProduto());
            psAtualizar.executeUpdate();

            ps.close();

        }
    }

    @Override
    public List<CarrinhoDTO> listarCarrinhoPorPessoa(String cpf) throws SQLException {
        List<CarrinhoDTO> carrinho = new ArrayList<>();
        String sql = "SELECT c.cpf, c.idProduto, c.quantidade, c.preco_total FROM Carrinho c WHERE c.cpf = ?";

        try (Connection connection = Conexao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, cpf);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                CarrinhoDTO carrinhoDTO = new CarrinhoDTO(
                        cpf,
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
    public void finalizarCompra(String cpf) throws SQLException {
        String limparCarrinhoSql = "DELETE FROM Carrinho WHERE cpf = ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement psLimpar = connection.prepareStatement(limparCarrinhoSql)) {
            psLimpar.setString(1, cpf);
            psLimpar.executeUpdate();
        }
    }

    public String verificarUsuario(String email, String senha) throws SQLException {
        String verificarPessoaSql = "SELECT cpf FROM Pessoa WHERE email = ? AND senha = ?";
        try (Connection connection = Conexao.getConnection();
             PreparedStatement psVerificar = connection.prepareStatement(verificarPessoaSql)) {
            psVerificar.setString(1, email);
            psVerificar.setString(2, senha);
            ResultSet rsPessoa = psVerificar.executeQuery();

            if (rsPessoa.next()) {
                return rsPessoa.getString("cpf");
            } else {
                throw new RuntimeException(MensagensConstanteUtils.EMAIL_SENHA_INVALIDOS);
            }
        }
    }
}
