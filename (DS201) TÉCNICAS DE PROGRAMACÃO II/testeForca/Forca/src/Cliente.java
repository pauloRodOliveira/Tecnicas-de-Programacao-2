import java.net.*;
import java.io.*;
import lib.classescomuns.*;
import lib.classesclientes.*;

public class Cliente {
    
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

        char opcao = ' ';
        do{
            //System.out.println("Aguardando a sua vez de jogar ou o grupo de jogadores estar completo...");
            servidor.receba (new PedidoDeResultado ());
            Comunicado comunicado = null;
            
            // cliente fica esperando o resultado, quando o servidor mandar o resultado ele da continuidade ao programa
            /* NOTA: podemos utilizar isso para organizar a vez de cada jogador */
            do
            {
                comunicado = (Comunicado)servidor.espie (); 
            }
            while (!(comunicado instanceof Resultado));
            Resultado resultado = (Resultado)servidor.envie ();
            System.out.println ("Estado do jogo atual: "+resultado.getResultEstadoDoJogo()+"\n");

            // após ter entrado todos jogadores e for a vez de this
            System.out.println("Escolha entre [C]hutar (tentar uma letra), [R]esposta (tentar uma palavra) ou [S]air.");
            try{
                opcao = Character.toUpperCase(Teclado.getUmChar());
            }catch(Exception erro){
                System.out.println("Opcao inválida");
                continue; //não executa mais nada que vier pela frente e volta para o começo do loop
            }

            if("CRS".indexOf(opcao) == -1){
                System.out.println("Opcao inválida");
                continue;
            }

            if(opcao == 'S') break;

            try
            {
                if ("CR".indexOf(opcao)!=-1)
                {
                    System.out.print ("Digite sua tentativa: ");
                    String tenta = "";
                    try
                    {
                        // fazer o cliente digitar o valor do chute ou tentativa
                        if(opcao == 'C'){
                            tenta = Teclado.getUmString();
                        }else if(opcao == 'R'){
                            tenta = Teclado.getUmString();
                        }

                        if(opcao == 'C' && tenta.length() > 1){
                            System.out.println("Tentativa inválida");
                            continue;
                        }
                    }
                    catch (Exception erro)
                    {
                        System.err.println ("Tentativa inválida invalido!\n");
                        continue;
                    }

                    servidor.receba (new PedidoDeOperacao (opcao, tenta));
                }
            }
            catch (Exception erro)
            {
                System.err.println ("Erro de comunicacao com o servidor;");
                System.err.println ("Tente novamente!");
                System.err.println ("Caso o erro persista, termine o programa");
                System.err.println ("e volte a tentar mais tarde!\n");
            }
        }while(opcao != 'S');

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
