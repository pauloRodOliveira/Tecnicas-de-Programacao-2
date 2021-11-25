package classes.Servidor;

import classes1.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Reativo
{
    public static final String PORTA_PADRAO = "3000";
    public static void main (String[] args)
    {
        if(args.length>1){
            System.err.println("Uso esperado: java Servidor [PORTA]\n");
            return;
        }

        String porta = PORTA_PADRAO;

        if(args.length == 1) porta = args[0];

        /*do {
            ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
        }while (jogadores.lenght <=3);*/

        try {
            ServerSocket pedido = new ServerSocket(12345);

            // fio do telefone fixo preso na parede
            Socket conexao = pedido.accept(); // uma ponta do fio

            BufferedReader receptor = new BufferedReader(new InputStreamReader(conexao.getInputStream())); // autofalante
            //ObjectInputStream receptorDeObjeto = new ObjectInputStream()

            ObjectOutputStream transmissorDeObjeto = new ObjectOutputStream(conexao.getOutputStream()); // microfone de Objeto
            PrintWriter transmissor = new PrintWriter(conexao.getOutputStream()); // microfone
            // não é necessário um transmissor, pq ele só vai receber, não vai mandar

            String texto;

            /*do
            {
                texto = receptor.readLine ();
                System.out.println (texto);
                transmissor.println("você digitou isso: " + texto);
                transmissor.flush();
            }
            while (!texto.toUpperCase().equals ("FIM"));*/


            char continuar = ' ';
            boolean b = false;
            do {
                Palavra palavra =
                        BancoDePalavras.getPalavraSorteada();

                Tracinhos tracinhos = null;
                try {
                    tracinhos = new Tracinhos(palavra.getTamanho());
                } catch (Exception erro) {
                }

                ControladorDeLetrasJaDigitadas
                        controladorDeLetrasJaDigitadas =
                        new ControladorDeLetrasJaDigitadas();

                ControladorDeErros controladorDeErros = null;
                try {
                    controladorDeErros = new ControladorDeErros((int) (palavra.getTamanho() * 0.6));
                } catch (Exception erro) {
                }

                while (tracinhos.isAindaComTracinhos() &&
                        !controladorDeErros.isAtingidoMaximoDeErros()) {
                    System.out.println("Palavra...: " + tracinhos);
                    System.out.println("Digitadas.: " + controladorDeLetrasJaDigitadas);
                    System.out.println("Erros.....: " + controladorDeErros);
                    transmissor.println("Palavra...: " + tracinhos);
                    transmissor.println("Digitadas.: " + controladorDeLetrasJaDigitadas);
                    transmissor.println("Erros.....: " + controladorDeErros);
                    transmissor.flush();

                    b = false;

                    try {
                        System.out.print("Qual letra? ");
                        transmissor.println("Qual letra? ");
                        transmissor.flush();
                        b = true;
                        String prov = receptor.readLine();
                        char letra = prov.charAt(0);

                        if (controladorDeLetrasJaDigitadas.isJaDigitada(letra)) {
                            System.err.println("Essa letra ja foi digitada!\n");
                            transmissor.println("Essa letra ja foi digitada!\n");
                            transmissor.flush();
                        }
                        else {
                            controladorDeLetrasJaDigitadas.registre(letra);

                            int qtd = palavra.getQuantidade(letra);

                            if (qtd == 0) {
                                System.err.println("A palavra nao tem essa letra!\n");
                                transmissor.println("A palavra nao tem essa letra!\n");
                                transmissor.flush();
                                controladorDeErros.registreUmErro();
                            } else {
                                for (int i = 0; i < qtd; i++) {
                                    int posicao = palavra.getPosicaoDaIezimaOcorrencia(i, letra);
                                    tracinhos.revele(posicao, letra);
                                }
                                System.out.println();
                                transmissor.println();
                                transmissor.flush();
                            }
                        }
                    } catch (Exception erro) {
                        System.err.println(erro.getMessage());
                        transmissor.println(erro.getMessage());
                        transmissor.flush();
                    }
                }

                if (controladorDeErros.isAtingidoMaximoDeErros()) {
                    System.out.println("Que pena! Voce perdeu! A palavra era " + palavra + "\n");
                    transmissor.println("Que pena! Voce perdeu! A palavra era " + palavra + "\n");
                    transmissor.flush();
                }
                else{ // !tracinhos.isAindaComTracinhos()
                    System.out.println("Parabens! Voce ganhou! A palavra era mesmo " + palavra + "\n");
                    transmissor.println("Parabens! Voce ganhou! A palavra era mesmo " + palavra + "\n");
                    transmissor.flush();
                }

                for (; ; ) {
                    try {
                        System.out.print("Deseja jogar de novo (S/N)? ");
                        transmissor.print("Deseja jogar de novo (S/N)? ");
                        transmissor.flush();
                        continuar = Character.toUpperCase(Teclado.getUmChar());
                        if (continuar != 'S' && continuar != 'N') {
                            System.err.println("Opcao invalida! Tente novamente...");
                            transmissor.println("Opcao invalida! Tente novamente...");
                            transmissor.flush();
                        }
                        else
                            break;
                    } catch (Exception erro) {
                        System.err.println("Opcao invalida! Tente novamente...");
                        transmissor.println("Opcao invalida! Tente novamente...");
                        transmissor.flush();
                    }
                }
            }
            while (continuar == 'S');

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


