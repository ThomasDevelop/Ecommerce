package com.ecommerce.Ecommerce.usecase;

import com.ecommerce.Ecommerce.dao.impl.CarrinhoDAOImpl;
import com.ecommerce.Ecommerce.dto.CarrinhoDTO;

import java.sql.SQLException;
import java.util.List;

public class CarrinhoService {
    private CarrinhoDAOImpl carrinhoDAOImpl = new CarrinhoDAOImpl();

    public void adicionarAoCarrinho(String cpf, int idProduto, int quantidade) {
        CarrinhoDTO carrinhoDTO = new CarrinhoDTO(cpf, idProduto, quantidade, 0.0);
        try {
            carrinhoDAOImpl.adicionarAoCarrinho(carrinhoDTO);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao adicionar produto ao carrinho");
        }
    }

    public void listarCarrinho(String cpf) {
        List<CarrinhoDTO> carrinho = null;
        try {
            carrinho = carrinhoDAOImpl.listarCarrinhoPorPessoa(cpf);

            if (carrinho != null && !carrinho.isEmpty()) {
                System.out.println("-------------------\nLista de Carrinho do usuário com o CPF: " + cpf);
                for (CarrinhoDTO c : carrinho) {
                    System.out.println("ID Produto: " + c.getIdProduto() +
                            "\nQuantidade: " + c.getQuantidade() +
                            "\nPreço Total: " + c.getPrecoTotal());
                    System.out.println("-------------------");
                }
            } else {
                System.out.println("Nenhum produto encontrado no carrinho");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar carrinho: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Erro ao listar carrinho para o usuário com CPF: " + cpf, e);
        }
    }

    public void finalizarCompra(String email, String senha) {
        try {
            String cpf = carrinhoDAOImpl.verificarUsuario(email, senha);

            List<CarrinhoDTO> carrinho = carrinhoDAOImpl.listarCarrinhoPorPessoa(cpf);
            double valorTotalCompra = carrinho.stream().mapToDouble(CarrinhoDTO::getPrecoTotal).sum();

            if (carrinho.isEmpty()) {
                System.out.println("Não há itens no carrinho\n");
                return;
            }
            System.out.println("Itens no carrinho:");
            for (CarrinhoDTO item : carrinho) {
                System.out.println("Produto ID: " + item.getIdProduto() +
                        ", Quantidade: " + item.getQuantidade() +
                        ", Preço Total: " + item.getPrecoTotal());
            }
            System.out.println("Valor total da compra: " + valorTotalCompra +"\n");

            carrinhoDAOImpl.finalizarCompra(cpf);
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