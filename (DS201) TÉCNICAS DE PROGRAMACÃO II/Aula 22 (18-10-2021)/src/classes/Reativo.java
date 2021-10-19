package classes;

import java.io.*;
import java.net.*;

public class Reativo
{
    public static void main (String[] args)
    {
        try
        {
            ServerSocket pedido  = new ServerSocket (12345);

            // fio do telefone fixo preso na parede
            Socket conexao = pedido.accept(); // uma ponta do fio

            BufferedReader receptor = new BufferedReader (new InputStreamReader ( conexao.getInputStream ())); // autofalante
            //ObjectInputStream receptorDeObjeto = new ObjectInputStream()

            ObjectOutputStream transmissorDeObjeto = new ObjectOutputStream(conexao.getOutputStream()); // microfone de Objeto
            PrintWriter transmissor = new PrintWriter (conexao.getOutputStream ()); // microfone
            // não é necessário um transmissor, pq ele só vai receber, não vai mandar

            String texto;

            do
            {
                texto = receptor.readLine ();
                System.out.println (texto);
                transmissor.println("você digitou isso: " + texto);
                transmissor.flush();
            }
            while (!texto.toUpperCase().equals ("FIM"));


            transmissor.close();
            transmissorDeObjeto.close();
            receptor.close();
            conexao.close();

            pedido.close();
        }
        catch (Exception erro)
        {
            System.err.println (erro.getMessage());
        }
    }
}

