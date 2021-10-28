package com.company;

import static java.lang.System.*;
import classes.*;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
	    out.println("Bem-vindo à Calculadora Trigonométrica. \n");
        out.println("Primeiro, seu triângulo é retângulo? \n");
        out.println("Digite S ou N");
        char res = (char) System.in.read();

        try {
            if (Character.toUpperCase(res) == 'S') {
                out.println("\nPor favor, digite o valor de cada lado: ");
                out.println("Primeiro lado: ");
                int lad1 = System.in.read();
                out.println("Segundo lado: ");
                int lad2 = System.in.read();
                out.println("Terceiro lado: ");
                int lad3 = System.in.read();
            }
        }catch(Exception erro){
            out.println("Erro em coletar dados do triângulo: " + erro);
        }
    }
}
