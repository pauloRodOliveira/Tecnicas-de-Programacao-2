package lib.classesservidor;

import java.io.Serializable;

public class Palavra implements Comparable<Palavra>, Serializable
{
    private String texto;

    public Palavra (String texto) throws Exception
    {
        // verifica se o texto recebido é nulo ou então vazio,
        // ou seja, sem nenhum caractere, lançando exceção.
        // armazena o texto recebido em this.texto.

        if (texto == null) throw new Exception ("Esse campo está vazio");
        if (texto == "") throw new Exception ("Esse campo está vazio");
        if (texto == " ") throw new Exception ("Esse campo está vazio");

        this.texto = texto;
    }

    public int getQuantidade (char letra)
    {
        // percorre o String this.texto, conta e retorna
        // quantas letras existem nele iguais a letra fornecida

        int quantidade = 0;
        int tampalavra = this.texto.length();

        for (int posicao = 0; posicao < tampalavra; posicao++)
        {
            if (letra == texto.charAt(posicao))
                quantidade++;
        }
        return quantidade;
    }

    public int getPosicaoDaIezimaOcorrencia (int i, char letra) throws Exception
    {
        // se i==0, retorna a posicao em que ocorre a primeira
        // aparicao de letra fornecida em this.texto;
        // se i==1, retorna a posicao em que ocorre a segunda
        // aparicao de letra fornecida em this.texto;
        // se i==2, retorna a posicao em que ocorre a terceira
        // aparicao de letra fornecida em this.texto;
        // e assim por diante.
        // lançar excecao caso nao encontre em this.texto
        // a Iézima aparição da letra fornecida.

        int quantidade = 0;
        int tampalavra = this.texto.length();

        for (int posicao = 0; posicao < tampalavra; posicao++)
        {
            if (letra == texto.charAt(posicao))
            {
                if (quantidade == i)
                {
                    return posicao;
                }
                else
                {
                    quantidade++;
                }
            }
        }
        throw new Exception ("Desculpe, não foi possível encontrar a letra!");
    }

    public int getTamanho ()
    {
        return this.texto.length();
    }

    public String toString ()
    {
        return this.texto;
    }

    public boolean equals (Object obj)
    {
        // verificar se this e obj possuem o mesmo conteúdo, retornando
        // true no caso afirmativo ou false no caso negativo

        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        Palavra other = (Palavra) obj;
        if (texto == null)
        {
            if (other.texto != null)
                return false;
        }
        else if (!texto.equals(other.texto))
            return false;
        return true;
    }

    public int hashCode ()
    {
        // calcular e retornar o hashcode de this
        int ret = 1;

        ret = 2 * ret + new Integer(this.texto).hashCode();

        return ret;
    }

    public int compareTo (Palavra palavra)
    {
        return this.texto.compareTo(palavra.texto);
    }
}

