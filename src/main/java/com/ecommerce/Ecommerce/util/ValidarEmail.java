package com.ecommerce.Ecommerce.util;

public class ValidarEmail {

    public static boolean validar(String email) {
        return email.contains("@") && email.endsWith(".com");
    }
}