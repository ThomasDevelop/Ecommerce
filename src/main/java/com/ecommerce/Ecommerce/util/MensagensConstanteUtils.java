package com.ecommerce.Ecommerce.util;

public class MensagensConstanteUtils {
    //-----------------------------------             CARRINHO            -------------------------------//
    //----------------------------------- ERROS EXCEPTIONS CARRINHO_SERVICE------------------------------//
    public static final String ERRO_PRODUTO_PARA_CARRINHO = "Erro ao adicionar produto ao carrinho";
    public static final String ERRO_AO_LISTAR_CARRINHO = "Erro ao listar carrinho: ";
    public static final String ERRO_AO_LISTAR_CARRINHO_POR_CPF = "Erro ao listar carrinho para o usuário com CPF: ";
    public static final String ERRO_AO_FINALIZAR_COMPRA = "Erro ao finalizar a compra: ";
    public static final String ERRO_AO_FINALIZAR_COMPRA_PARA_USUARIO_EMAIL = "Erro ao finalizar a compra para o usuário com o email: ";
    public static final String ERRO_INESPERADO = "Ocorreu um erro inesperado ao finalizar a compra.";

    //----------------------------------- ERROS CARRINHO_SERVICE-----------------------------------------//
    public static final String NENHUM_PRODUTO_ENCONTRADO = "Nenhum produto encontrado no carrinho";
    public static final String NAO_TEM_ITENS_NO_CARRINHO = "Não há itens no carrinho\n";

    //----------------------------------- ERROS CARRINHO_DAO---------------------------------------------//
    public static final String PRODUTO_NAO_ENCONTRADO_DAO = "Produto não encontrado.";
    public static final String USUARIO_COM_CPF = "Usuário com o CPF ";
    public static final String NAO_ENCONTRADO_COM_CPF = " não encontrado.";
    public static final String QUANTIDADE_INSUFICIENTE_NO_ESTOQUE = "Quantidade insuficiente no estoque.";

    //----------------------------------- ERROS EXCEPTION CARRINHO_DAO-----------------------------------//
    public static final String EMAIL_SENHA_INVALIDOS = "Email ou senha inválidos.";





    //-----------------------------------             PRODUTO            ------------    ----------------//
    //----------------------------------- ERROS EXCEPTIONS PRODUTO_SERVICE-------------------------------//
    public static final String ERRO_AO_ADICIONAR_PRODUTO = "Erro ao adicionar produto: ";

    //----------------------------------- ERROS PRODUTO_SERVICE------------------------------------------//
    public static final String NENHUM_PRODUTO_ENCONTRADO_PRODUTO = "Nenhum produto encontrado.";





    //-----------------------------------             PESSOAS            --------------  ----------------//
    //----------------------------------- ERROS EXCEPTIONS PESSOA_SERVICE------------------- ------------//
    public static final String ERRO_AO_LISTAR_PESSOA = "Erro ao listar pessoa: ";
    public static final String ERRO_AO_ADICIONAR_PESSOA = "Erro ao adicionar pessoa: ";
    public static final String ERRO_AO_VERIFICAR_EMAIL_PESSOA = "Erro ao verificar o email: ";
    public static final String ERRO_AO_VERIFICAR_CPF_PESSOA = "Erro ao verificar o CPF: ";

    //----------------------------------- ERROS PESSOA_SERVICE-------------------------------------------//
    public static final String NENHUM_PESSOA_ENCONTRADA = "Nenhuma pessoa encontrada.";
    public static final String ERRO_O_EMAIL_JA_EXISTE = "Erro: O email já está cadastrado.";
    public static final String ERRO_O_CPF_JA_EXISTE = "Erro: O CPF já está cadastrado.";

    //----------------------------------- ERROS EXCEPTION PESSOA_DAO-------------------------------------//
    public static final String ERRO_PESSOA_EMAIL_EXISTENTE = "Erro ao adicionar pessoa: O email já existe.";
    public static final String ERRO_PESSOA_CPF_EXISTENTE = "Erro ao adicionar pessoa: O CPF já existe.";





    //-----------------------------------             MENU            -----------------------------------//
    //----------------------------------- ERROS EXCEPTIONS MENU------------------------------------------//
    public static final String ENTRADA_INVALIDA_NUMEROS = "Entrada inválida.";
    public static final String ERRO_LEITURA = "Erro de leitura: ";
    public static final String ERRO_INVALIDA_CPF_DEVE_CONTER_NUMEROS = "Entrada inválida. O CPF deve conter apenas números.";

    //----------------------------------- ERROS MENU-----------------------------------------------------//
    public static final String OPCAO_INVALIDA = "Opção inválida.";
    public static final String QUANTIDADE_INVALIDA = "Quantidade inválida.";
    public static final String OPCAO_INVALIDA_TENTE_NOVAMENTE = "Opção inválida. Tente novamente.";
    public static final String O_NOME_SO_PODE_LETRAS = "O Nome so pode conter letras. ";
    public static final String OPCAO_INVALIDA_DIGITE_OPCAO_NUMERICA = "Opção inválida. Digite uma opção numérica.";
    public static final String CPF_INVALIDO_DEVE_TER_11_DIGITO = "CPF inválido. O CPF deve conter exatamente 11 números.";
    public static final String ERRO_CPF_JA_CADASTRADO = "Erro: O CPF já está cadastrado.";
    public static final String ERRO_SENHA_INVALIDA_MINIMO_8 = "Senha inválida. A senha deve ter pelo menos 8 caracteres.";
    public static final String CPF_INVALIDO = "CPF inválido.";
    public static final String QUANTIDADE_MAIOR_QUE_ZERO = "A quantidade deve ser maior que zero.";
    public static final String EMAIL_INVALIDO = "Email inválido.";
    public static final String SENHA_INVALIDO = "Senha inválida.";
    public static final String PRECO_INVALIDO = "Preço inválido.";
    public static final String INSIRA_EMAIL_VALIDO = "Email inválido. Insira um email válido.";
    public static final String EMAIL_JA_CADASTRADO = "Erro: O email já está cadastrado.";
    public static final String ID_PRODUTO_INVALIDO = "ID do produto invalido.";
}
