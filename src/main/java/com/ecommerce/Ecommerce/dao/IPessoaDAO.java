package com.ecommerce.Ecommerce.dao;

import com.ecommerce.Ecommerce.dto.PessoaDTO;

import java.sql.SQLException;
import java.util.List;

public interface IPessoaDAO {
    List<PessoaDTO> listarPessoas() throws SQLException;
    void adicionarPessoa(PessoaDTO pessoaDTO) throws SQLException;
}