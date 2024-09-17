package com.ecommerce.Ecommerce.dao;

import com.ecommerce.Ecommerce.dto.CarrinhoDTO;

import java.sql.SQLException;
import java.util.List;

public interface ICarrinhoDAO {
    void adicionarAoCarrinho(CarrinhoDTO carrinhoDTO) throws SQLException;
    List<CarrinhoDTO> listarCarrinhoPorPessoa(int idPessoa) throws SQLException;
    void finalizarCompra(int idPessoa) throws SQLException;
}