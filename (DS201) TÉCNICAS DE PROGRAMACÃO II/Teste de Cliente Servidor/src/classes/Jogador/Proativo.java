package classes.Jogador;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import classes1.*;

public class Proativo
{
    public static void main (String[] args)
    {
        try
        {
            // fio do telefone fixo preso na parede
            Socket conexao = new Socket ("localhost",12345);
                                                // ^ pode colocar ip

            BufferedReader receptor =
            new BufferedReader (
            new InputStreamReader (
            conexao.getInputStream ())); // autofalante

            PrintWriter transmissor = new PrintWriter (conexao.getOutputStream ()); // microfone

            Character texto;
            Character aux;
            String recebido;
            Teclado teclado = new Teclado();

            do
            {



                texto = teclado.getUmChar();
                transmissor.println (texto);
                transmissor.flush (); // para envio imediato
                recebido = receptor.readLine();
                System.out.println(recebido);
                aux = Character.toUpperCase(texto);
            }
            while (!aux.equals ("S"));

            transmissor.close();

            receptor.close();

            conexao.close();
        }
        catch (Exception erro)
        {
            System.err.println (erro.getMessage());
        }
    }
}
