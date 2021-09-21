package com.company;
import java.lang.System;

public class Main {

    public static void main(String[] args) {
        String s = new String ("123");
        int i = Integer.parseInt(s);
        Integer.parseInt(s);
        // ^ Chamante.^ Método
        //Chamante pode ser uma Classe ou um Objeto.
        /*Quando o método ser static, ele não vai poder ter um Objeto como chamante, somente uma Classe.
        * Por exemplo, o método Integer.parseInt(s); este método é estático, portanto não é necessário criar um
        * Objeto da Classe Wrapper Integer para usar este método.
        * */
        var out = System.out;
        out.println(i);
    }
}
