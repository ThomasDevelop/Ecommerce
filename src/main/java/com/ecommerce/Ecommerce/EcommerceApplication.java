package com.ecommerce.Ecommerce;

import com.ecommerce.Ecommerce.enuns.MenuCarrinhoEnuns;
import com.ecommerce.Ecommerce.enuns.MenuPrincipalEnuns;
import com.ecommerce.Ecommerce.enuns.MenuProdutoEnuns;
import com.ecommerce.Ecommerce.enuns.MenuUsuarioEnuns;
import com.ecommerce.Ecommerce.usecase.CarrinhoService;
import com.ecommerce.Ecommerce.usecase.PessoaService;
import com.ecommerce.Ecommerce.usecase.ProdutoService;
import com.ecommerce.Ecommerce.util.MensagensConstanteUtils;
import com.ecommerce.Ecommerce.util.ValidaOpcao;
import com.ecommerce.Ecommerce.util.ValidarEmail;
import com.ecommerce.Ecommerce.util.ValidarSenha;

import java.util.InputMismatchException;
import java.util.Scanner;

public class EcommerceApplication {
    static Scanner scanner = new Scanner(System.in);
    private static byte opcao;
    private static ProdutoService produtoService = new ProdutoService();
    private static PessoaService pessoaService = new PessoaService();
    private static CarrinhoService carrinhoService = new CarrinhoService();

    public static void main(String[] args){
        exibirMenuPrincipal();
    }

    public static void exibirMenuPrincipal() {
        while (true) {
            System.out.println(MenuPrincipalEnuns.MENU_PRINCIPAL_ENUNS.getMensagem());


            String entrada = scanner.nextLine();
            if (ValidaOpcao.eNumerico(entrada)) {
                opcao = Byte.parseByte(entrada);
                switch (opcao) {
                    case 1:
                        exibirMenuProduto();
                        break;
                    case 2:
                        exibirMenuPessoa();
                        break;
                    case 3:
                        exibirMenuCarrinho();
                    case 0:
                        System.out.println("Programa finalizado.");
                        System.exit(0);
                    default:
                        System.out.println(MensagensConstanteUtils.OPCAO_INVALIDA_TENTE_NOVAMENTE);
                        break;
                }
            } else {
                System.out.println(MensagensConstanteUtils.OPCAO_INVALIDA);
            }
        }
    }

    public static void exibirMenuProduto() {
        while (true) {
            System.out.println(MenuProdutoEnuns.MENU_PRODUTO_ENUNS.getMensagem());

            String entrada = scanner.nextLine();
            if (ValidaOpcao.eNumerico(entrada)) {
                opcao = Byte.parseByte(entrada);
                switch (opcao) {
                    case 1:
                        produtoService.listarProdutos();
                        break;
                    case 2:
                        String nome;
                        int quantidade = -1;
                        double preco = -1.0;
                        do {
                            System.out.println("Digite o nome do produto:");
                            nome = scanner.nextLine();

                            if (!nome.matches("[a-zA-Z\\s]+")) {
                                System.out.println(MensagensConstanteUtils.O_NOME_SO_PODE_LETRAS);
                            }
                        } while (!nome.matches("[a-zA-Z\\s]+"));

                        do {
                            System.out.println("Digite a quantidade do produto:");
                            String quantidadeStr = scanner.nextLine();

                            try {
                                quantidade = Integer.parseInt(quantidadeStr);
                                if (quantidade <= 0) {
                                    System.out.println(MensagensConstanteUtils.QUANTIDADE_INVALIDA);
                                    quantidade = -1;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println(MensagensConstanteUtils.ENTRADA_INVALIDA_NUMEROS);
                            }
                        } while (quantidade <= 0);

                        do {
                            System.out.println("Digite o preço do produto:");
                            String precoStr = scanner.nextLine();

                            try {
                                preco = Double.parseDouble(precoStr);
                                if (preco <= 0) {
                                    System.out.println(MensagensConstanteUtils.PRECO_INVALIDO);
                                    preco = -1.0;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println(MensagensConstanteUtils.ENTRADA_INVALIDA_NUMEROS);
                            }

                        } while (preco <= 0);
                        produtoService.adicionarProduto(nome, quantidade, preco);
                        System.out.println("Produto adicionado");
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println(MensagensConstanteUtils.OPCAO_INVALIDA_TENTE_NOVAMENTE);
                        break;
                }
            } else {
                System.out.println(MensagensConstanteUtils.OPCAO_INVALIDA_DIGITE_OPCAO_NUMERICA);
            }
        }
    }

    public static void exibirMenuPessoa() {
        while (true) {
            System.out.println(MenuUsuarioEnuns.MENU_USUARIO_ENUNS.getMensagem());

            String entrada = scanner.nextLine();
            if (ValidaOpcao.eNumerico(entrada)) {
                opcao = Byte.parseByte(entrada);

                switch (opcao) {
                    case 1:
                        PessoaService.listarPessoa();
                        break;
                    case 2:
                        adicionarPessoa();
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println(MensagensConstanteUtils.OPCAO_INVALIDA_TENTE_NOVAMENTE);
                        break;
                }
            } else {
                System.out.println(MensagensConstanteUtils.OPCAO_INVALIDA_DIGITE_OPCAO_NUMERICA);
            }
        }
    }

    private static void adicionarPessoa() {
        try {
            String nome;
            String email;
            String cpf;
            String senha;

            do {
                System.out.println("Digite o seu nome:");
                nome = scanner.nextLine();
                if (!nome.matches("[a-zA-Z\\s]+")) {
                    System.out.println(MensagensConstanteUtils.O_NOME_SO_PODE_LETRAS);
                }
            } while (!nome.matches("[a-zA-Z\\s]+"));
            do {
                System.out.println("Digite o email:");
                email = scanner.nextLine();
                if (!ValidarEmail.validar(email)) {
                    System.out.println(MensagensConstanteUtils.INSIRA_EMAIL_VALIDO);
                } else if (pessoaService.emailExiste(email)) {
                    System.out.println(MensagensConstanteUtils.EMAIL_JA_CADASTRADO);
                    return;
                }
            } while (!ValidarEmail.validar(email));
            do {
                System.out.println("Digite o CPF:");
                cpf = scanner.nextLine();
                if (!cpf.matches("\\d{11}")) {
                    System.out.println(MensagensConstanteUtils.CPF_INVALIDO_DEVE_TER_11_DIGITO);
                } else if (pessoaService.cpfExiste(cpf)) {
                    System.out.println(MensagensConstanteUtils.ERRO_CPF_JA_CADASTRADO);
                    return;
                }
            } while (!cpf.matches("\\d{11}"));
            do {
                System.out.println("Digite sua senha (deve ter 8 ou mais caracteres):");
                senha = scanner.nextLine();
                if (!ValidarSenha.validar(senha)) {
                    System.out.println(MensagensConstanteUtils.ERRO_SENHA_INVALIDA_MINIMO_8);
                }
            } while (!ValidarSenha.validar(senha));
            pessoaService.adicionarPessoa(nome, email, cpf, senha);
            System.out.println("Usuário adicionado com sucesso!");
        } catch (InputMismatchException e) {
            System.out.println(MensagensConstanteUtils.ERRO_LEITURA);
        }
    }

    public static void exibirMenuCarrinho() {
        while (true) {
            System.out.println(MenuCarrinhoEnuns.MENU_CARRINHO_ENUNS.getMensagem());

            String entrada = scanner.nextLine();
            if (ValidaOpcao.eNumerico(entrada)) {
                opcao = Byte.parseByte(entrada);
            }
            switch (opcao) {
                case 1:
                    adicionarProdutoAoCarrinho();
                    break;
                case 2:
                    finalizarCompra();
                    break;
                case 3:
                    System.out.println("Digite o seu CPF:");
                    String cpf  = scanner.nextLine();
                    carrinhoService.listarCarrinho(cpf);
                    break;
                case 0:
                    exibirMenuPrincipal();
                    return;
                default:
                    System.out.println(MensagensConstanteUtils.OPCAO_INVALIDA);
                    break;
            }
        }
    }

    private static void adicionarProdutoAoCarrinho() {
        String cpf ;
        int idProduto = -1;
        int quantidade = -1;

        while (true) {
            try {
                System.out.println("Digite o seu CPF (apenas números):");
                String inputPessoa = scanner.nextLine();

                if (inputPessoa.matches("\\d{11}")) {
                    cpf = inputPessoa;
                    break;
                } else {
                    System.out.println(MensagensConstanteUtils.CPF_INVALIDO);
                }
            } catch (Exception e) {
                System.out.println(MensagensConstanteUtils.ERRO_INVALIDA_CPF_DEVE_CONTER_NUMEROS);
            }
        }

        while (idProduto < 0) {
            try {
                System.out.println("Digite o ID do produto:");
                String inputProduto = scanner.nextLine();
                idProduto = Integer.parseInt(inputProduto);
                if (idProduto < 0) {
                    System.out.println(MensagensConstanteUtils.ID_PRODUTO_INVALIDO);
                }
            } catch (NumberFormatException e) {
                System.out.println(MensagensConstanteUtils.ENTRADA_INVALIDA_NUMEROS);
            }
        }
        while (quantidade <= 0) {
            try {
                System.out.println("Digite a quantidade desejada:");
                String inputQuantidade = scanner.nextLine();
                quantidade = Integer.parseInt(inputQuantidade);
                if (quantidade <= 0) {
                    System.out.println(MensagensConstanteUtils.QUANTIDADE_MAIOR_QUE_ZERO);
                }
            } catch (NumberFormatException e) {
                System.out.println(MensagensConstanteUtils.ENTRADA_INVALIDA_NUMEROS);
            }
        }
        carrinhoService.adicionarAoCarrinho(cpf, idProduto, quantidade);
        System.out.println("----------------------" + "\nID Produto: " + idProduto + "\nQuantidade: " + quantidade + "\n----------------------");
    }

    private static void finalizarCompra() {
        String email;
        String senha;
        do {
            System.out.println("Digite o seu email:");
            email = scanner.nextLine();
            if (!ValidarEmail.validar(email)) {
                System.out.println(MensagensConstanteUtils.EMAIL_INVALIDO);
            }
        } while (!ValidarEmail.validar(email));
        do {
            System.out.println("Digite a sua senha:");
            senha = scanner.nextLine();
            if (!ValidarSenha.validar(senha)) {
                System.out.println(MensagensConstanteUtils.SENHA_INVALIDO);
            }
        } while (!ValidarSenha.validar(senha));
        carrinhoService.finalizarCompra(email, senha);
    }
}