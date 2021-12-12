import java.net.*;
import java.io.*;
import lib.classescomuns.*;
import lib.classesclientes.*;

public class Cliente {

    // Esperar por outros jogadores
        // condicional, quando ele receber um comunicado de que todos os jogadores entraram
        // ele vai receber uma mensagem informando-o disso

        // repetição
        {
            // receber estado de jogo atual
            // se ele receber um comunicado do tipo estado atual, ele vai mostrá-lo

            //jogar 
            // caso ele receba um comunicado de permição para jogar, ele joga
        }

    
    public static final String HOST_PADRAO  = "localhost";
	public static final int    PORTA_PADRAO = 3000;

    public static void main(String[] args) throws Exception {
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

            if (args.length>0)
                host = args[0];

            if (args.length==2)
                porta = Integer.parseInt(args[1]);

            conexao = new Socket (host, porta);
        }
        catch (Exception erro)
        {
            System.err.println ("Indique o servidor e a porta corretos!\n");
            return;
        }

        ObjectOutputStream transmissor=null;
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
        try
        {
            servidor =
            new Parceiro (conexao, receptor, transmissor); //este vai o ser o representante do servidor no programa do cliente
        }
        catch (Exception erro)
        {
            System.err.println ("Indique o servidor e a porta corretos!\n");
            return;
        }

        //Vai tratar os comunicados de desligamento
        TratadoraDeComunicadoDeDesligamento tratadoraDeComunicadoDeDesligamento = null;
        try
        {
            tratadoraDeComunicadoDeDesligamento = new TratadoraDeComunicadoDeDesligamento (servidor);
        }
        catch (Exception erro)
        {} // sei que servidor foi instanciado
            
        tratadoraDeComunicadoDeDesligamento.start();

        /*System.out.println ("Aguardando jogadores entrarem...\n");
        servidor.receba (new PedidoDeResultado ());
        Comunicado comunicado = null;
        do
        {
            comunicado = (Comunicado)servidor.espie ();
        }
        while (!(comunicado instanceof Permicao));
        Permicao  permicao = (Permicao)servidor.envie ();
        System.out.println ("Todos jogadores já entraram, agora aguarde sua vez...\n");*/

        
        char opcao=' ';
        do
        {
            //caso ele receba um comunicado permitindo
            // joga a letra da opçao escolhida 
            servidor.receba (new PedidoDeResultado ());
            Comunicado estado = null;
            do
            {
                estado = (Comunicado)servidor.espie ();
            }
            while (!(estado instanceof EstadoDeJogo));
            EstadoDeJogo estadoDeJogo = (EstadoDeJogo)servidor.envie ();
            System.out.println ("Estado de jogo atual: "+ estadoDeJogo.getResultEstadoDoJogo()+"\n");

            System.out.print ("Sua opção: [C]hute (somente uma letra), [R]esposta (palavra inteira) e [S]air. \n");
            System.out.println("NOTA: caso escolha Resposta e ERRE, você será ELIMINADO!");

            try
            {
                opcao = Character.toUpperCase(Teclado.getUmChar());
            }
            catch (Exception erro)
            {
                
                System.err.println ("Opcao invalida!\n");
                continue;
            }

            if ("CRS".indexOf(opcao)==-1)
            {
                System.err.println ("Opcao invalida!\n");
                continue;
            }

            if(opcao == 'S') break;

            try
            {
                System.out.println("Faça sua tentativa: ");
                if(opcao == 'C'){
                    char letra;
                    try
                    {
                        letra = Character.toUpperCase(Teclado.getUmChar());
                    }
                    catch (Exception erro)
                    {
                        System.err.println ("Opcao inválida, ou letra já digitada!\n");
                        continue;
                    }

                    if(estadoDeJogo.isJaDigitada(letra)){
                        System.out.println("Letra já digitada");
                    }

                    servidor.receba(new PedidoDeOperacao(opcao, letra));
                }else{
                    String resposta;
                    try
                    {
                        resposta = Teclado.getUmString();
                    }
                    catch (Exception erro)
                    {
                        System.err.println ("Opcao invalida!\n");
                        continue;
                    }
                    servidor.receba(new PedidoDeOperacao(opcao, resposta));
                    System.out.print("Resultado:");
                    servidor.receba (new PedidoDeDecisao ());
                    Comunicado decisao = (Comunicado)servidor.espie();
                    if(decisao instanceof Vitoria){
                        System.out.println("Você ganhou");
                    }else if(decisao instanceof Derrota){
                        System.out.println("Você perdeu");
                    }

                    opcao = 'S';
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
        while (opcao != 'S');

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
