package com.ecommerce.Ecommerce.enuns;

public enum MenuPrincipalEnuns {

    MENU_PRINCIPAL_ENUNS("""
            ╔════════════════════════════════════════════╗
            ║                 Ecommerce                  ║
            ║                                            ║
            ║  Escolha uma opção digitando entre         ║
            ║  (1, 2, 3 ou 0 para sair");                ║
            ║                                            ║
            ║ [1] - Produto                              ║
            ║ [2] - Cadastre-se                          ║
            ║ [3] - Fazer compra                         ║
            ║ [0] - Sair                                 ║
            ╚════════════════════════════════════════════╝
            Digite uma opção:""");

    private final String mensagem;

    MenuPrincipalEnuns(String mensagem) {
        this.mensagem = mensagem;
    }
    public String getMensagem() {
        return mensagem;
    }
}