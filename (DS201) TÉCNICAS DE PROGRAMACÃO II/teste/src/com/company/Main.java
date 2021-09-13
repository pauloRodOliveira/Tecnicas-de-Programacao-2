package com.company;
import java.io.*;
import classes.Teste;

public class Main {

    public static void main(String[] args) {
        try{
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Digite um número que queira dobrar o valor: ");

            byte n = Byte.parseByte(teclado.readLine());

            Teste numeroDigitado = new Teste(n);
            System.out.println(numeroDigitado.setDoubleValueOf(n));

        }catch (Exception e){
        }
    }
}
