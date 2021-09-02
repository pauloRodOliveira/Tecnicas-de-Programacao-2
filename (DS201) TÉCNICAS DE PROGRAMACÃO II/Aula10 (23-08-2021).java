// aula 23/08/2021

import java.io.*;
/*
* O ".*" vai selecionar todos, caso queira selecionar somente um faça o seguinte:
* import java.io.BufferedReader;
* import java.io.InputStreamReader;
* import java.io.IOException;
* */

public class Main
{
	public static void main(String[] args) {
        System.out.println("Hello world");
        System.out.print("Nao pula linha (print), ");
        System.out.println("pula linha (println).");
        
        //A classe BufferedReader é reservada para leitores (input/entrada de dados).

        // Neste caso ele vai ler os inputs do teclado.
        BufferedReader teclado = new BufferedReader (new InputStreamReader (System.in));
        System.out.print("Como você se chama? ");
        String nome = "";
        try {
            nome = teclado.readLine();
            System.out.println("Bem vindo ao estudo de Java ;-;, " + nome);
        }catch (IOException e)
            {System.out.print("nao foi");}

        /*// Agora ele vai receber um arquivo
        BufferedReader arquivo = new BufferedReader (new FileReader ("c:\\Downloads\\Arquivo.txt"));
        //vai ler cada linha do arquivo e mostrar ela
         while (arquivo.ready())
           System.out.println(arquivo.readLine());*/
        
        int qtd = 0;
        
        for (;;) /*vai se repetir para sempre*/
        {
            System.out.println("Quantas vezes você pretende fazer esta disciplina? ");
            
            try{
                qtd = Integer.parseInt(teclado.readLine());
                
                if (qtd <= 0)
                    System.out.println(nome + ", com essa quantidade de vez(es), é quase certo que tu vai repetir essa disciplina diversas vezes");
                else
                    break;
            }
            catch(IOException e)
            {}
            catch(NumberFormatException e){
                System.err.println("A quantidade de vexes deve ser um número inteiro, tente novamente");
            }
        }
        
     }
	}

