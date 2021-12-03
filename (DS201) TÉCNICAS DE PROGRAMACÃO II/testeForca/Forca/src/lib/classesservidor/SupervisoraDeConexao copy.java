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
                ArrayList<Parceiro> grupo = new ArrayList<Parceiro>(3);
                this.usuarios.trimToSize(); //regula o tamanho do array para o a quantidade de elementos que tiverem dentro dele
                this.usuarios.add (this.usuario);
                // vou poder utilizar size pois o .trimToSize() regula o tamanho do ArrayList
                if(usuarios.size() >= 3){     
                    for(int aux = 0; aux <= this.usuarios.size(); aux++){
                        grupo.set(aux, this.usuarios.get(aux));
                    }
                }
            }


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
						case '+':
						    this.valor += pedidoDeOperacao.getValor();
						    break;
						    
						case '-':
						    this.valor -= pedidoDeOperacao.getValor();
						    break;
						    
						case '*':
						    this.valor *= pedidoDeOperacao.getValor();
						    break;
						    
						case '/':
						    this.valor /= pedidoDeOperacao.getValor();
                    }
                }
                else if (comunicado instanceof PedidoDeResultado)
                {
                    this.usuario.receba (new Resultado (this.valor));
                }
                else if (comunicado instanceof PedidoParaSair)
                {
                    synchronized (this.usuarios)
                    {
                        this.usuarios.remove (this.usuario);
                        this.usuarios.trimToSize(); //faz a capacidade do array ser igual ao número de elementos dentro dele.
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
