package com.ecommerce.Ecommerce;

import com.ecommerce.Ecommerce.usecase.CarrinhoService;
import com.ecommerce.Ecommerce.usecase.PessoaService;
import com.ecommerce.Ecommerce.usecase.ProdutoService;
import com.ecommerce.Ecommerce.util.ValidaOpcao;
import com.ecommerce.Ecommerce.util.ValidarEmail;
import com.ecommerce.Ecommerce.util.ValidarSenha;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EcommerceApplication {
    static Scanner scanner = new Scanner(System.in);
    private static byte opcao;
    private static ProdutoService produtoService = new ProdutoService();
    private static PessoaService pessoaService = new PessoaService();
    private static CarrinhoService carrinhoService = new CarrinhoService();

    public static void main(String[] args) throws SQLException {
        exibirMenuPrincipal();
    }

    public static void exibirMenuPrincipal() {
        while (true) {
            System.out.println(" Ecommerce\nEscolha uma opção digitando entre 1, 2, 3 ou 0 para sair\n");
            System.out.println("[1] Produto");
            System.out.println("[2] Cadastre-se");
            System.out.println("[3] Fazer a Compra");
            System.out.println("[0] Para sair.");

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
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            } else {
                System.out.println("Opção inválida.");
            }
        }
    }

    public static void exibirMenuProduto() {
        while (true) {
            System.out.println("Sessão Menu Produto\n");
            System.out.println("[1] Ver Produtos.");
            System.out.println("[2] Adicionar Produto.");
            System.out.println("[0] Voltar para o menu principal.");

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
                                System.out.println("O Nome so pode conter letras. ");
                            }
                        } while (!nome.matches("[a-zA-Z\\s]+"));

                        do {
                            System.out.println("Digite a quantidade do produto:");
                            String quantidadeStr = scanner.nextLine();

                            try {
                                quantidade = Integer.parseInt(quantidadeStr);
                                if (quantidade <= 0) {
                                    System.out.println("Quantidade inválida.");
                                    quantidade = -1;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Entrada inválida. So pode numeros");
                            }
                        } while (quantidade <= 0);

                        do {
                            System.out.println("Digite o preço do produto:");
                            String precoStr = scanner.nextLine();

                            try {
                                preco = Double.parseDouble(precoStr);
                                if (preco <= 0) {
                                    System.out.println("Preço inválido.");
                                    preco = -1.0;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Entrada inválida.");
                            }
                        } while (preco <= 0);
                        produtoService.adicionarProduto(nome, quantidade, preco);
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            } else {
                System.out.println("Opção inválida. Digite uma opção numérica.");
            }
        }
    }

    public static void exibirMenuPessoa() {
        while (true) {
            System.out.println("Sessão Menu Usuario\n");
            System.out.println("[1] Ver Usuarios.");
            System.out.println("[2] Adicionar Usuario.");
            System.out.println("[0] Voltar para o menu principal.");

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
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            } else {
                System.out.println("Opção inválida. Digite uma opção numérica.");
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
                    System.out.println("O nome deve ser com letras.");
                }
            } while (!nome.matches("[a-zA-Z\\s]+"));
            do {
                System.out.println("Digite o email:");
                email = scanner.nextLine();
                if (!ValidarEmail.validar(email)) {
                    System.out.println("Email inválido. Insira um email válido.");
                } else if (pessoaService.emailExiste(email)) {
                    System.out.println("Erro: O email já está cadastrado.");
                    return;
                }
            } while (!ValidarEmail.validar(email));
            do {
                System.out.println("Digite o CPF:");
                cpf = scanner.nextLine();
                if (!cpf.matches("\\d{11}")) {
                    System.out.println("CPF inválido. O CPF deve conter exatamente 11 números.");
                } else if (pessoaService.cpfExiste(cpf)) {
                    System.out.println("Erro: O CPF já está cadastrado.");
                    return;
                }
            } while (!cpf.matches("\\d{11}"));
            do {
                System.out.println("Digite sua senha (deve ter 8 ou mais caracteres):");
                senha = scanner.nextLine();
                if (!ValidarSenha.validar(senha)) {
                    System.out.println("Senha inválida. A senha deve ter pelo menos 8 caracteres.");
                }
            } while (!ValidarSenha.validar(senha));
            pessoaService.adicionarPessoa(nome, email, cpf, senha);
            System.out.println("Usuário adicionado com sucesso!");
        } catch (InputMismatchException e) {
            System.out.println("Erro de input: " + e.getMessage());
        }
    }

    public static void exibirMenuCarrinho() {
        while (true) {
            System.out.println("[1] Adicionar Produto ao Carrinho");
            System.out.println("[2] Finalizar Compra");
            System.out.println("[3] Produtos no carrinho");
            System.out.println("[0] Voltar");

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
                    System.out.println("Digite o seu ID:");
                    int idPessoa = Integer.parseInt(scanner.nextLine());
                    carrinhoService.listarCarrinho(idPessoa);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }

    private static void adicionarProdutoAoCarrinho() {
        int idPessoa = -1;
        int idProduto = -1;
        int quantidade = -1;

        while (idPessoa < 0) {
            try {
                System.out.println("Digite o seu ID:");
                String inputPessoa = scanner.nextLine();
                idPessoa = Integer.parseInt(inputPessoa);
                if (idPessoa < 0) {
                    System.out.println("Id invalido.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. O id Deve ser numero.");
            }
        }
        while (idProduto < 0) {
            try {
                System.out.println("Digite o ID do produto:");
                String inputProduto = scanner.nextLine();
                idProduto = Integer.parseInt(inputProduto);
                if (idProduto < 0) {
                    System.out.println("O ID do produto invalido.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida.");
            }
        }
        while (quantidade <= 0) {
            try {
                System.out.println("Digite a quantidade desejada:");
                String inputQuantidade = scanner.nextLine();
                quantidade = Integer.parseInt(inputQuantidade);
                if (quantidade <= 0) {
                    System.out.println("A quantidade deve ser maior que zero.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida.");
            }
        }
        carrinhoService.adicionarAoCarrinho(idPessoa, idProduto, quantidade);
        System.out.println("Produto adicionado ao carrinho:" + "\nID Produto: " + idProduto + "\nQuantidade: " + quantidade);
    }

    private static void finalizarCompra() {
        String email;
        String senha;
        do {
            System.out.println("Digite o seu email:");
            email = scanner.nextLine();
            if (!ValidarEmail.validar(email)) {
                System.out.println("Email inválido.");
            }
        } while (!ValidarEmail.validar(email));
        do {
            System.out.println("Digite a sua senha:");
            senha = scanner.nextLine();
            if (!ValidarSenha.validar(senha)) {
                System.out.println("Senha inválida.");
            }
        } while (!ValidarSenha.validar(senha));
        carrinhoService.finalizarCompra(email, senha);
    }
}