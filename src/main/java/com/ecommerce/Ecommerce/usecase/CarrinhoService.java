package com.ecommerce.Ecommerce.usecase;

import com.ecommerce.Ecommerce.dao.impl.CarrinhoDAOImpl;
import com.ecommerce.Ecommerce.dto.CarrinhoDTO;
import com.ecommerce.Ecommerce.util.MensagensConstanteUtils;

import java.sql.SQLException;
import java.util.List;

public class CarrinhoService {
    private CarrinhoDAOImpl carrinhoDAOImpl = new CarrinhoDAOImpl();

    public void adicionarAoCarrinho(String cpf, int idProduto, int quantidade) {
        CarrinhoDTO carrinhoDTO = new CarrinhoDTO(cpf, idProduto, quantidade, 0.0);
        try {
            carrinhoDAOImpl.adicionarAoCarrinho(carrinhoDTO);
        } catch (SQLException e) {

            System.out.println(MensagensConstanteUtils.ERRO_PRODUTO_PARA_CARRINHO);
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
                System.out.println(MensagensConstanteUtils.NENHUM_PRODUTO_ENCONTRADO);
            }
        } catch (SQLException e) {
            System.out.println(MensagensConstanteUtils.ERRO_AO_LISTAR_CARRINHO);

            throw new RuntimeException(MensagensConstanteUtils.ERRO_AO_LISTAR_CARRINHO_POR_CPF + cpf, e);
        }
    }

    public void finalizarCompra(String email, String senha) {
        try {
            String cpf = carrinhoDAOImpl.verificarUsuario(email, senha);

            List<CarrinhoDTO> carrinho = carrinhoDAOImpl.listarCarrinhoPorPessoa(cpf);
            double valorTotalCompra = carrinho.stream().mapToDouble(CarrinhoDTO::getPrecoTotal).sum();

            if (carrinho.isEmpty()) {
                System.out.println(MensagensConstanteUtils.NAO_TEM_ITENS_NO_CARRINHO);
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
            System.out.println(MensagensConstanteUtils.ERRO_AO_FINALIZAR_COMPRA);
            throw new RuntimeException(MensagensConstanteUtils.ERRO_AO_FINALIZAR_COMPRA_PARA_USUARIO_EMAIL + email, e);
        } catch (Exception e) {
            throw new RuntimeException(MensagensConstanteUtils.ERRO_INESPERADO, e);
        }
    }
}