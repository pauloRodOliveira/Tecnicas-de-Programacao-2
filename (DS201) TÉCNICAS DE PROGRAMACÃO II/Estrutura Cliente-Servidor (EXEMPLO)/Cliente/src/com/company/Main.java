package com.company;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.*;
import java.io.*;

public class Main {
    /* ****** ETAPA 1 ****** */
        public static final String HOST_PADRAO  = "localhost";
        //             ^ é uma string constante
        public static final int    PORTA_PADRAO = 3000;
        //             ^ é um int constante

        public static void main (String[] args)
        {
            if (args.length>2)
            {
                System.err.println ("Uso esperado: java Cliente [HOST [PORTA]]\n");
                return;
            }

            Socket conexao=null;
            try
            {
                String host = Cliente.HOST_PADRAO;
                int    porta= Cliente.PORTA_PADRAO;

                if (args.length>0) // se o vetor args for maior que 0, ele vai tirar o host padrão
                    host = args[0];
                //    ^ host recebe a posição 0 do vetor args

                if (args.length==2) // se o vetor args for == a 2, ele vai tirar a porta padrão
                    porta = Integer.parseInt(args[1]);
                                //            ^ porta recebe a posição 1 do vetor args

                conexao = new Socket (host, porta);
                // conexão com o Socket
            }
            catch (Exception erro)
            {
                System.err.println ("Indique o servidor e a porta corretos!\n");
                return;
            }

            /* ****** ETAPA 2 ****** */
            ObjectOutputStream transmissor=null;
            // vai transmitir para o servidor
            try
            {
                transmissor =
                        new ObjectOutputStream(
                                conexao.getOutputStream());
            }
            catch (Exception erro)
            {
                System.err.println ("Indique o servidor e a porta corretos!\n");
                return;
            }

            ObjectInputStream receptor=null;
            // receber do Servidor
            try
            {
                receptor =
                        new ObjectInputStream(
                                conexao.getInputStream());
            }
            catch (Exception erro)
            {
                System.err.println ("Indique o servidor e a porta corretos!\n");
                return;
            }

            Parceiro servidor=null;
            // utilizado para se comunicar com o servidor
            try
            {
                servidor =
                        new Parceiro (conexao, receptor, transmissor);
            }
            catch (Exception erro)
            {
                System.err.println ("Indique o servidor e a porta corretos!\n");
                return;
            }

            /* ****** ETAPA 3 ****** */
            TratadoraDeComunicadoDeDesligamento tratadoraDeComunicadoDeDesligamento = null;
            try
            {
                tratadoraDeComunicadoDeDesligamento = new TratadoraDeComunicadoDeDesligamento (servidor);
            }
            catch (Exception erro)
            {} // sei que servidor foi instanciado

            tratadoraDeComunicadoDeDesligamento.start();

            char opcao=' ';
            do
            {
                System.out.print ("Sua opcao (+, -, *, /, =, [T]erminar)? ");

                try
                {
                    opcao = Character.toUpperCase(Teclado.getUmChar());
                }
                catch (Exception erro)
                {
                    System.err.println ("Opcao invalida!\n");
                    continue;
                }

                if ("+-*/=T".indexOf(opcao)==-1)
                {
                    System.err.println ("Opcao invalida!\n");
                    continue;
                }

                try
                {
                    double valor=0;
                    if ("+-*/".indexOf(opcao)!=-1)
                    {
                        System.out.print ("Valor? ");
                        try
                        {
                            valor = Teclado.getUmDouble();
                            System.out.println();

                            if (opcao=='/' && valor==0)
                            {
                                System.err.println ("Valor invalido!\n");
                                continue;
                            }
                        }
                        catch (Exception erro)
                        {
                            System.err.println ("Valor invalido!\n");
                            continue;
                        }


                        servidor.receba (new PedidoDeOperacao (opcao,valor));
                        // ^ Faz o Servidor receber
                    }
                    else if (opcao=='=')
                    {
                        servidor.receba (new PedidoDeResultado ());
                        Comunicado comunicado = null;
                        do
                        {
                            comunicado = (Comunicado)servidor.espie ();
                        }
                        while (!(comunicado instanceof Resultado));
                        //                      ^ verifica se é uma instância de Resultado
                        Resultado resultado = (Resultado)servidor.envie ();
                        //                                          ^ envia como um resultado qualquer
                        System.out.println ("Resultado atual: "+resultado.getValorResultante()+"\n");
                    }
                }
                catch (Exception erro)
                {
                    System.err.println ("Erro de comunicacao com o servidor;");
                    System.err.println ("Tente novamente!");
                    System.err.println ("Caso o erro persista, termine o programa");
                    System.err.println ("e volte a tentar mais tarde!\n");
                }
            }
            while (opcao != 'T');

            try
            {
                servidor.receba (new PedidoParaSair ());
            }
            catch (Exception erro)
            {}

            System.out.println ("Obrigado por usar este programa!");
            System.exit(0);
        }
    }