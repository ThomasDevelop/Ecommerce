package com.ecommerce.Ecommerce.enuns;

public enum MenuProdutoEnuns {

    MENU_PRODUTO_ENUNS("""
            ╔════════════════════════════════════════════╗
            ║            Sessão Menu Produto             ║
            ║ [1] - Produtos disponíveis                 ║
            ║ [2] - Adicionar Produto                    ║
            ║ [0] - Voltar para o menu principal         ║
            ╚════════════════════════════════════════════╝
            Digite uma opção:""");

    private final String mensagem;

    MenuProdutoEnuns(String mensagem) {
        this.mensagem = mensagem;
    }
    public String getMensagem() {
        return mensagem;
    }
}