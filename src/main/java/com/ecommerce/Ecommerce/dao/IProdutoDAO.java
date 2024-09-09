package com.ecommerce.Ecommerce.dao;

import com.ecommerce.Ecommerce.dto.ProdutoDTO;

import java.sql.SQLException;
import java.util.List;

public interface IProdutoDAO {
    List<ProdutoDTO> listarProduto() throws SQLException;
    void adicionarProduto(ProdutoDTO produtoDTO) throws SQLException;
}