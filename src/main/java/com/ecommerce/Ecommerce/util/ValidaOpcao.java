package com.ecommerce.Ecommerce.util;

public class ValidaOpcao {
        public static boolean eNumerico(String str) {
            try {
                Byte.parseByte(str);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }