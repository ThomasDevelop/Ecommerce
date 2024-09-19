package com.ecommerce.Ecommerce.dao;

import com.ecommerce.Ecommerce.dto.CarrinhoDTO;

import java.sql.SQLException;
import java.util.List;

public interface ICarrinhoDAO {
    void adicionarAoCarrinho(CarrinhoDTO carrinhoDTO) throws SQLException;
    List<CarrinhoDTO> listarCarrinhoPorPessoa(String cpf) throws SQLException;
    void finalizarCompra(String cpf) throws SQLException;
}