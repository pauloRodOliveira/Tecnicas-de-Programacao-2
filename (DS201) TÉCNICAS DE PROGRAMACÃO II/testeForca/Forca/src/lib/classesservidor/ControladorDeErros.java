package lib.classesservidor;

import java.io.Serializable;

public class ControladorDeErros implements Cloneable, Serializable
{
    private int qtdMax, qtdErr=0;

    public ControladorDeErros (int qtdMax) throws Exception
    {
        // verifica se qtdMax fornecida não é positiva, lançando
        // uma exceção.
        // armazena qtdMax fornecida em this.qtdMax.
        if(qtdMax < 0) throw new Exception("Quantidade de erros inválida");

        this.qtdMax = qtdMax;
    }

    public void registreUmErro () throws Exception
    {
        // verifica se this.qtdErr ja é igual a this.qtdMax,
        // lançando excecao em caso positivo ou
        // incrementando this.qtdErr em caso negativo
        if(this.qtdErr == this.qtdMax) throw new Exception("Quantidade de erro igual a quantidade limite");

        this.qtdErr++;
    }

    public boolean isAtingidoMaximoDeErros  ()
    {
        // returna true se this.qtdErr for igual a this.qtdMax,
        // ou então false, caso contrario.
        if(this.qtdErr == this.qtdMax) return true;

        return false;
    }

    @Override
    public String toString ()
    {
        return this.qtdErr + "/" + this.qtdMax;
    }

    @Override
    public boolean equals (Object obj)
    {
        // verificar se this e obj possuem o mesmo conteúdo, retornando
        // true no caso afirmativo ou false no caso negativo
        boolean ver = true;
        if(obj == null) ver = false;

        ControladorDeErros aux = (ControladorDeErros)obj;

        if (aux.qtdMax != this.qtdMax) ver = false;
        if (aux.qtdErr != this.qtdErr) ver = false;
        return ver;
    }

    @Override
    public int hashCode ()
    {
        // calcular e retornar o hashcode de this
        int ret = 1;

        ret = 2*ret + new Integer(this.qtdErr).hashCode();
        ret = 2*ret + new Integer(this.qtdMax).hashCode();

        return ret; // Paulo: to colocando return para não dar erro na hora de compilar, mas pode apagar depois
    }

    public ControladorDeErros (ControladorDeErros c) throws Exception // construtor de cópia
    {
        // copiar c.qtdMax e c.qtdErr, respectivamente em, this.qtdMax e this.qtdErr
        if(c == null) throw new Exception("Objeto dentro do construtor de copia está nulo. ");

        this.qtdMax = c.qtdMax;
        this.qtdErr = c.qtdErr;
    }

    public Object clone ()
    {
        // retornar uma cópia de this
        ControladorDeErros copia = null;

        try {
            copia = new ControladorDeErros(this);
        }catch (Exception erro) {System.err.println(erro);}

        return copia;
    }
}

