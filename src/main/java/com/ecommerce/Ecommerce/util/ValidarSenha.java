package com.ecommerce.Ecommerce.util;

public class ValidarSenha {
    public static boolean validar(String senha) {
        return senha.length() >= 8;
    }
}