package com.ecommerce.Ecommerce;

import com.ecommerce.Ecommerce.usecase.CarrinhoService;
import com.ecommerce.Ecommerce.usecase.PessoaService;
import com.ecommerce.Ecommerce.usecase.ProdutoService;
import com.ecommerce.Ecommerce.util.ValidaOpcao;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EcommerceApplication {
    public static Scanner scanner = new Scanner(System.in);
    private static byte opcao;
    private static ProdutoService produtoService = new ProdutoService();
    private static PessoaService pessoaService = new PessoaService();
    private static CarrinhoService carrinhoService = new CarrinhoService();

    public static void main(String[] args) throws SQLException {
        exibirMenuPrincipal();
    }

    public static void exibirMenuPrincipal() throws SQLException {
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
                System.out.println("Opção inválida. Digite uma opção numérica.");

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
                        System.out.println("Digite o nome do produto:");
                        String nome = scanner.nextLine();

                        System.out.println("Digite a quantidade do produto:");
                        int quantidade = Integer.parseInt(scanner.nextLine());

                        System.out.println("Digite o preço do produto:");
                        double preco = Double.parseDouble(scanner.nextLine());

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

    public static void exibirMenuPessoa() throws SQLException {
        while (true) {
            System.out.println("Sessão Menu Pessoa\n");
            System.out.println("[1] Ver Pessoas.");
            System.out.println("[2] Adicionar Pessoa.");
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
            System.out.println("Digite o nome da pessoa:");
            String nome = scanner.nextLine();

            System.out.println("Digite o email da pessoa:");
            String email = scanner.nextLine();

            System.out.println("Digite o CPF da pessoa:");
            String cpf = scanner.nextLine();

            System.out.println("Digite a senha da pessoa:");
            String senha = scanner.nextLine();

            pessoaService.adicionarPessoa(nome, email, cpf, senha);
            System.out.println("Pessoa adicionada com sucesso!");
        } catch (InputMismatchException e) {
            System.out.println("Erro de input: " + e.getMessage());
        }
    }

    public static void exibirMenuCarrinho() {
        while (true) {
            System.out.println("[1] Adicionar Produto ao Carrinho");
            System.out.println("[2] Finalizar Compra");
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
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }

    private static void adicionarProdutoAoCarrinho() {
        System.out.println("Digite o seu ID:");
        int idPessoa = Integer.parseInt(scanner.nextLine());

        System.out.println("Digite o ID do produto:");
        int idProduto = Integer.parseInt(scanner.nextLine());

        System.out.println("Quantidade desejada:");
        int quantidade = Integer.parseInt(scanner.nextLine());

        carrinhoService.adicionarAoCarrinho(idPessoa, idProduto, quantidade);
        System.out.println("Produto adicionado ao carrinho.");
    }

    private static void finalizarCompra() {
        System.out.println("Digite o seu email:");
        String email = scanner.nextLine();

        System.out.println("Digite a sua senha:");
        String senha = scanner.nextLine();

        carrinhoService.finalizarCompra(email, senha);
        System.out.println("Compra Realizada.");
    }
}