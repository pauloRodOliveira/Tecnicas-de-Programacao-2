package lib.classesservidor;
import java.io.*;
import java.net.*; // Para usar sockets
import java.util.*;
import lib.classescomuns.*;

public class SupervisoraDeConexao extends Thread
{
    private String              palavra;
    private char                  letra;
    private Boolean      vitoriaDerrota;
    private Parceiro            usuario;
    private Socket              conexao;
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
            /*
            synchronized (this.usuarios)
            {
                ArrayList<Parceiro> grupo = new ArrayList<Parceiro>(3);
                this.usuarios.trimToSize(); //regula o tamanho do array para o a quantidade de elementos que tiverem dentro dele
                this.usuarios.add (this.usuario);
                // vou poder utilizar size pois o .trimToSize() regula o tamanho do ArrayList
                if(usuarios.size() >= 3){     
                    for(int aux = 0; aux <= this.usuarios.size(); aux++){
                        grupo.set(aux, this.usuarios.get(aux));
                    }
                }
            }*/

            synchronized (this.usuarios)
            {
                this.usuarios.add (this.usuario);
            }

            
            Palavra palavra = BancoDePalavras.getPalavraSorteada();
            
            Tracinhos tracinhos = null;
            try
            {
                tracinhos = new Tracinhos (palavra.getTamanho());
            }
            catch (Exception erro)
            {}
            

            ControladorDeLetrasJaDigitadas
                    controladorDeLetrasJaDigitadas =
                    new ControladorDeLetrasJaDigitadas ();

            ControladorDeErros controladorDeErros = null;
            try
            {
                controladorDeErros = new ControladorDeErros (1);
            }
            catch (Exception erro)
            {}

            for(;;)
            {
                Comunicado comunicado = this.usuario.envie ();

                if (comunicado==null)
                    return;
                else if (comunicado instanceof PedidoDeOperacao)
                {
					PedidoDeOperacao pedidoDeOperacao = (PedidoDeOperacao)comunicado;
					
					switch (pedidoDeOperacao.getOperacao())
					{
						case 'C':
						    this.letra = pedidoDeOperacao.getLetra();
						    break;
						    
						case 'R':
						    this.palavra = pedidoDeOperacao.getPalav();
						    break;
                    }

                    if(pedidoDeOperacao.getOperacao() == 'C'){
                        if(controladorDeLetrasJaDigitadas.isJaDigitada(letra)){
                            throw new Exception("letra já digitada");
                        }else{
                            controladorDeLetrasJaDigitadas.registre(letra);
                        }

                        int qtd = palavra.getQuantidade (letra);

                        if (qtd==0)
                        {
                            throw new Exception("A palavra nao tem essa letra!\n");
                        }
                        else
                        {
                            for (int i=0; i<qtd; i++)
                            {
                                int posicao = palavra.getPosicaoDaIezimaOcorrencia (i,letra);
                                tracinhos.revele (posicao, letra);
                            }
                        }
                    }else{
                        if(this.palavra.toUpperCase().equals(palavra.getPalavra())){
                            this.vitoriaDerrota  = true;
                        }else{
                            this.vitoriaDerrota  = false;
                        }
                    }
                }
                else if (comunicado instanceof PedidoDeResultado)
                {
                    this.usuario.receba (new EstadoDeJogo (tracinhos, controladorDeLetrasJaDigitadas));
                    //this.usuario.receba (new RespostaDeJogo (tracinhos, resposta));
                }
                else if (comunicado instanceof PedidoDeDecisao)
                {
                        if(this.vitoriaDerrota){ usuario.receba(new Vitoria());}
                        else if(!this.vitoriaDerrota){ usuario.receba(new Derrota());}
                }
                else if (comunicado instanceof PedidoParaSair)
                {
                    synchronized (this.usuarios)
                    {
                        this.usuarios.remove (this.usuario);
                    }
                    this.usuario.adeus();
                }
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
