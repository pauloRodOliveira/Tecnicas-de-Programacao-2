package classes;

import java.io.*; // para ler e receber dados
import java.net.*; // para transmitir dados

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

            BufferedReader teclado =
                    new BufferedReader (
                            new InputStreamReader (
                                    System.in));

            String texto;
            String recebido;

            do
            {
                texto = teclado.readLine ();
                transmissor.println (texto);
                transmissor.flush (); // para envio imediato
                recebido = receptor.readLine();
                System.out.println(recebido);
            }
            while (!texto.toUpperCase().equals ("FIM"));

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
