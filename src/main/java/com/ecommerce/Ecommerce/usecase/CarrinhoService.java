package com.ecommerce.Ecommerce.usecase;

import com.ecommerce.Ecommerce.dao.impl.CarrinhoDAOImpl;
import com.ecommerce.Ecommerce.dto.CarrinhoDTO;
import com.ecommerce.Ecommerce.dto.ProdutoDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarrinhoService {
    private CarrinhoDAOImpl carrinhoDAOImpl = new CarrinhoDAOImpl();

    public void adicionarAoCarrinho(int idPessoa, int idProduto, int quantidade) {
        CarrinhoDTO carrinhoDTO = new CarrinhoDTO(idPessoa, idProduto, quantidade, 0.0);
        try {
            carrinhoDAOImpl.adicionarAoCarrinho(carrinhoDTO);
        } catch (SQLException e) {
            System.out.println("erro ao adicionar produto ao carrinho");
        }
    }
    public void listarCarrinho(int idPessoa){
        List<CarrinhoDTO> carrinho = null;
        try {
            carrinho = carrinhoDAOImpl.listarCarrinhoPorPessoa(idPessoa);

            if (carrinho != null && !carrinho.isEmpty()){
                System.out.println("-------------------\nLista de Carrinho da Pessoa com ID: " + idPessoa);
                for (CarrinhoDTO c : carrinho){
                    System.out.println("ID Produto: " + c.getIdProduto() +
                            "\nQuantidade: " + c.getQuantidade() +
                            "\nPreço Total: " + c.getPrecoTotal());
                    System.out.println("-------------------");
                }
            } else {
                System.out.println("Nenhum produto encontrado no carrinho para a pessoa com ID: " + idPessoa);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar carrinho: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Erro ao listar carrinho para a pessoa com ID: " + idPessoa, e);
        }
    }


    public void finalizarCompra(String email, String senha) {
        try {
            carrinhoDAOImpl.finalizarCompra(email, senha);
        } catch (SQLException e) {
            System.err.println("Erro ao finalizar a compra: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Erro ao finalizar a compra para o usuário com o email: " + email, e);
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Ocorreu um erro inesperado ao finalizar a compra.", e);
        }
    }
}