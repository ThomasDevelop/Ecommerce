package com.ecommerce.Ecommerce.enuns;

public enum MenuUsuarioEnuns {

    MENU_USUARIO_ENUNS("""
            ╔════════════════════════════════════════════╗
            ║            Sessão Menu usuario             ║
            ║ [1] - Ver Usuarios                         ║
            ║ [2] - Adicionar Usuario                    ║
            ║ [0] - Voltar para o menu principal         ║
            ╚════════════════════════════════════════════╝
            Digite uma opção:""");

    private final String mensagem;

    MenuUsuarioEnuns(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}