package com.ecommerce.Ecommerce.usecase;


import com.ecommerce.Ecommerce.dao.impl.ProdutoDAOImpl;
import com.ecommerce.Ecommerce.dto.ProdutoDTO;
import com.ecommerce.Ecommerce.util.MensagensConstanteUtils;

import java.util.List;

public class ProdutoService {
    private static ProdutoDAOImpl produtoDaoImpl = new ProdutoDAOImpl();

    public void listarProdutos() {
        List<ProdutoDTO> produtos = produtoDaoImpl.listarProduto();
        if (produtos != null && !produtos.isEmpty()) {
            for (ProdutoDTO p : produtos) {
                System.out.println("-------------------\nLista de Produto");
                System.out.println("ID: " + p.getIdProduto() + "\nNome: " + p.getNome() +
                        "\nQuantidade: " + p.getQuantidade() + "\nPreço: " + p.getPreco());
                System.out.println("-------------------");
            }
        } else {
            System.out.println(MensagensConstanteUtils.NENHUM_PRODUTO_ENCONTRADO_PRODUTO);
        }
    }

    public void adicionarProduto(String nome, int quantidade, double preco) {
        ProdutoDTO produtoDTO = new ProdutoDTO(null, nome, quantidade, preco);
        try {
            produtoDaoImpl.adicionarProduto(produtoDTO);
        } catch (Exception e) {
            System.out.println(MensagensConstanteUtils.ERRO_AO_ADICIONAR_PRODUTO);
        }
    }
}