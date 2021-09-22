/*
atividade autonoma dia 09-09-2021
*/
package com.company;
import java.io.*;
import classes.*;
import static java.lang.System.*;
import static java.lang.Math.*;

public class Main
{
    public static void main(String[] args) throws Exception {
       // para Data
        /*byte dia;
        byte mes;
        short ano;
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

        do {
            out.println("Digite um ano: ");
            ano = Short.parseShort(teclado.readLine());
            if (ano < -3500 || ano == 0) out.println("Digite um ano válido!!! De preferência, quando o sapiens já aprendeu escrever");
            if (ano > -3500 && ano != 0)break;
        } while (ano < -3500 || ano == 0);
        Data conferirAno = new Data(ano);

        do {
            out.println("Digite um mes: ");
            mes = Byte.parseByte(teclado.readLine());
            if (mes < 1 || mes > 12) out.println("Digite um mes válido!!!");
            if (mes > 1 && mes < 12) break;
        } while (mes < 1 || mes > 12);

        boolean diaCerto = false;
        do {
            out.println("Digite um dia: ");
            dia = Byte.parseByte(teclado.readLine());

            if (dia < 1 || dia > 31) out.println("Digite um dia válido!!!");

            if (mes == 2 && dia > 29 && Data.isBissexto(ano)){
                out.println("Digite um dia válido!!!");
            }else{
                diaCerto = true;
                if (dia > 1 && dia < 31) break;
            }
        } while (dia < 1 || dia > 31 || !diaCerto);

        try {
            Data d1 = new Data(dia, mes, ano);
            d1.tranformeSeNoDiaSeguinte();
            out.println(d1);
            d1.tranformeSeNoDiaAnterior();
            out.println(d1);
            out.println(d1.getDiaSeguinte());
            out.println("Estado do Objeto: " + d1);
            out.println(d1.getDiaAnterior());
            out.println("Estado do Objeto: " + d1);

        } catch (Exception e) {
            out.println(e);
        }*/

        // para Horario
        Horario Tempo = new Horario((byte) 20, (byte) 24, (byte) 24);

        out.println("Hora: "+Tempo.getHora());
        out.println("Minuto: "+Tempo.getMin());
        out.println("Segundo: "+Tempo.getSeg());
        Tempo.setHora((byte)19);
        out.println("Nova hora: "+Tempo.getHora());
        Tempo.setMin((byte) 50);
        out.println("Novo minuto: "+Tempo.getMin());
        Tempo.setSeg((byte) 20);
        out.println("Novo segundo: "+Tempo.getSeg());
        //novo tudo precisa chamar o setTudo, porém para mostrar todos os atributos precisamos usar o ToString(), cujo método
        // precisa ser sobrescrito, ou seja, @Override
        Tempo.setTudo((byte)22,(byte)30,(byte)00);
        out.println("Novo tudo: "+Tempo.toString());
        int adiante = 120;
        Tempo.adiante(adiante);
        out.println(adiante+" adiante: "+Tempo.toString());

        // perguntar o que é ;;
    }
}
