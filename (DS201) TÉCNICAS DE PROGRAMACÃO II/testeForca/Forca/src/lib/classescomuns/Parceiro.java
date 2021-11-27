package lib.classescomuns;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.Semaphore;

// representa um cliente
// é executada junto com outros objetos parceiro, portanto é uma thread
public class Parceiro {
    private Socket             conexao;
    private ObjectInputStream  receptor;
    private ObjectOutputStream transmissor;

    private Comunicado proximoComunicado = null;

    private Semaphore mutEx = new Semaphore (1, true); // somente um Parceiro por vez para utilizar um recurso

    public Parceiro (Socket conexao, ObjectInputStream receptor, ObjectOutputStream transmissor) throws Exception
    {
        if (conexao==null)
            throw new Exception ("Conexao ausente");

        if (receptor==null)
            throw new Exception ("Receptor ausente");

        if (transmissor==null)
            throw new Exception ("Transmissor ausente");

        this.conexao     = conexao;
        this.receptor    = receptor;
        this.transmissor = transmissor;
    }

    // vai fazer a chamante receber
    public void receba (Comunicado x) throws Exception
    {
        try{
            this.transmissor.writeObject(x);
            this.transmissor.flush();
        }catch(Exception erro){
            throw new Exception ("Erro de transmissao");
        }
    }

    public Comunicado espie() throws Exception{
        try
        {
            this.mutEx.acquireUninterruptibly();
            if (this.proximoComunicado==null) this.proximoComunicado = (Comunicado)this.receptor.readObject();
            this.mutEx.release();
            return this.proximoComunicado;
        }
        catch (Exception erro)
        {
            throw new Exception ("Erro de recepcao");
        }
    }

    //vai fazer a chamante eviar
    public Comunicado envie() throws Exception
    {
        try
        {
            // se o proximo comunicado for nulo ele faz uma leitura e guarda no proximo comunicado
            if (this.proximoComunicado==null) this.proximoComunicado = (Comunicado)this.receptor.readObject();
            // caso nao seja nulo ou já tenha saído do if
            // ele vai guardar o que leu (ou já foi lido) no ret
            // vai fazer o proximo comunicado ficar nulo
            // e vai retornar o ret
            Comunicado ret         = this.proximoComunicado;
            this.proximoComunicado = null;
            return ret;
        }
        catch (Exception erro)
        {
            throw new Exception ("Erro de recepcao");
        }
    }

    public void adeus () throws Exception
    {
        try
        {
            this.transmissor.close();
            this.receptor   .close();
            this.conexao    .close();
        }
        catch (Exception erro)
        {
            throw new Exception ("Erro de desconexao");
        }
    }
}
