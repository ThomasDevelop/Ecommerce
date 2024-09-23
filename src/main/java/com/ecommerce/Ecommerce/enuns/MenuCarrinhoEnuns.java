package com.ecommerce.Ecommerce.enuns;
public enum MenuCarrinhoEnuns {

    MENU_CARRINHO_ENUNS("""
            ╔════════════════════════════════════════════╗
            ║            Sessão Menu Produto             ║
            ║ [1] - Adicionar Produto ao Carrinho        ║
            ║ [2] - Finalizar Compra                     ║
            ║ [0] - Voltar                               ║
            ╚════════════════════════════════════════════╝
            Digite uma opção:""");

    private final String mensagem;

    MenuCarrinhoEnuns(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}
