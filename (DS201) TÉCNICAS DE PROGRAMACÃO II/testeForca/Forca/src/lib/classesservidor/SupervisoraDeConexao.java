package lib.classesservidor;
import java.io.*;
import java.net.*; // Para usar sockets
import java.util.*;
import lib.classescomuns.*;

public class SupervisoraDeConexao extends Thread
{
    private String              palavra;
    private char                  letra;
    private String           estadoJogo;
    private Parceiro            usuario;
    private Socket              conexao;
    private ArrayList<Parceiro> grupoUsuarios;
    private ArrayList<Parceiro> usuarios;

    public SupervisoraDeConexao
    (Socket conexao, ArrayList<Parceiro> usuarios)//valida a classe
    throws Exception
    {
        if (conexao==null)
            throw new Exception ("Conexao ausente");

        if (usuarios==null)
            throw new Exception ("Usuarios ausentes");

        this.conexao  = conexao;
        this.usuarios = usuarios;
    }

    public void run ()
    {

        ObjectOutputStream transmissor;
        try
        {
            transmissor =
            new ObjectOutputStream(
            this.conexao.getOutputStream());
        }
        catch (Exception erro)
        {
            return;
        }
        
        ObjectInputStream receptor=null;
        try
        {
            receptor=
            new ObjectInputStream(
            this.conexao.getInputStream());
        }
        catch (Exception err0)
        {
            try
            {
                transmissor.close();
            }
            catch (Exception falha)
            {} // so tentando fechar antes de acabar a thread
            
            return;
        }

        try
        {
            this.usuario =
            new Parceiro (this.conexao,
                          receptor,
                          transmissor);
        }
        catch (Exception erro)
        {} // sei que passei os parametros corretos

        try
        {
            synchronized (this.usuarios)
            {
                // array list auxiliar recebe tres jogadores
                // forma uma array list GRUPO 
                // limpa auxiliar 
                this.usuarios.add (this.usuario);
                // quando o array list usuario tiver tres jogadores, ele vai montar um grupo com esses tres
                /*if(this.usuarios.size() == 3){
                    for(Parceiro usuario : this.usuarios){
                        this.grupoUsuarios.add(this.usuario);
                    }
                    this.usuarios.clear(); //limpa usuarios para acolher mais tres e montar outro grupo
                }*/
            }


            for(;;)
            {
                Comunicado comunicado = this.usuario.envie ();

                Palavra palavra =
                    BancoDePalavras.getPalavraSorteada();

                Tracinhos tracinhos = null;
                try
                {
                    tracinhos = new Tracinhos (palavra.getTamanho());
                }
                catch (Exception erro)
                {}

                ControladorDeLetrasJaDigitadas controladorDeLetrasJaDigitadas =
                    new ControladorDeLetrasJaDigitadas ();

                ControladorDeErros controladorDeErros = null;

                this.estadoJogo = "Palavra...: " + tracinhos + "Digitadas.: " + controladorDeLetrasJaDigitadas + "Erros.....: "+controladorDeErros;
                do{
                    if (comunicado==null)
                        return;
                    else if (comunicado instanceof PedidoDeOperacao)
                    {
                        PedidoDeOperacao pedidoDeOperacao = (PedidoDeOperacao)comunicado;
                        /*switch (pedidoDeOperacao.getOperacao())
                        {
                            case 'C':
                                String aux = pedidoDeOperacao.getTenta();
                                this.letra = aux.charAt(0);
                                break;
                                
                            case 'R':
                                this.palavra = pedidoDeOperacao.getTenta();
                                break;
                        }*/

                        while (tracinhos.isAindaComTracinhos() && !controladorDeErros.isAtingidoMaximoDeErros())
                         {
                             this.estadoJogo = "Palavra...: " + tracinhos +
                                               "Digitadas.: " + controladorDeLetrasJaDigitadas +
                                               "Erros.....: "+controladorDeErros;
                           // System.out.println ("Palavra...: "+tracinhos);
                           // System.out.println ("Digitadas.: "+controladorDeLetrasJaDigitadas);
                           // System.out.println ("Erros.....: "+controladorDeErros);

                            try
                            {
                                //System.out.print   ("Qual letra? "); // só vem na vez do cliente
                                char letra = pedidoDeOperacao.getOperacao(); //cliente

                                if (controladorDeLetrasJaDigitadas.isJaDigitada (letra))
                                    this.estadoJogo = "letra ja digitada";
                                else
                                {
                                    controladorDeLetrasJaDigitadas.registre (letra);

                                    int qtd = palavra.getQuantidade (letra);

                                    if (qtd==0)
                                    {
                                        //System.err.println ("A palavra nao tem essa letra!\n");
                                        controladorDeErros.registreUmErro ();
                                    }
                                    else
                                    {
                                        for (int i=0; i<qtd; i++)
                                        {
                                            int posicao = palavra.getPosicaoDaIezimaOcorrencia (i,letra);
                                            tracinhos.revele (posicao, letra);
                                        }
                                        //System.out.println ();
                                    }
                                }
                            }
                            catch (Exception erro)
                            {
                                //System.err.println (erro.getMessage());
                            }
                        }
                        try
                        {
                            controladorDeErros = new ControladorDeErros ((int)(palavra.getTamanho()*0.6));
                        }
                        catch (Exception erro)
                        {}

                    }
                    else if (comunicado instanceof PedidoDeResultado)
                    {
                        this.usuario.receba (new Resultado (this.estadoJogo));
                    }
                    else if (comunicado instanceof PedidoParaSair)
                    {
                    synchronized (this.usuarios)
                    {
                        this.usuarios.remove (this.usuario);
                    }
                    this.usuario.adeus();
                    }
                }while(!(comunicado instanceof PedidoParaSair));
            }
        }
        catch (Exception erro)
        {
            try
            {
                transmissor.close ();
                receptor   .close ();
            }
            catch (Exception falha)
            {} // so tentando fechar antes de acabar a thread

            return;
        }
    }
}
