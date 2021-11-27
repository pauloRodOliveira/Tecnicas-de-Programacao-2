package lib.classesservidor;

public class Tracinhos implements Cloneable
{
    private char texto [];

    public Tracinhos (int qtd) throws Exception
    {
        // verifica se qtd não é positiva, lançando uma exceção.
        if(qtd <= 0) throw new Exception ("Palavra não encontrada");

        // instancia this.texto com um vetor com tamanho igual qtd.
        this.texto= new char[qtd];

        // preenche this.texto com underlines ().
        for(int i= 0; i<(qtd); i++)
            this.texto[i]= '_';
    }

    public void revele (int posicao, char letra) throws Exception
    {
        // verifica se posicao é negativa ou então igual ou maior
        // do que this.texto.length, lançando uma exceção.
        if(posicao < 0 || posicao >= this.texto.length)
            throw new Exception ("Posição não encontrada");

        // armazena a letra fornecida na posicao tambem fornecida
        // do vetor this.texto
        if(this.texto[posicao] != '_')
            throw new Exception ("Posição já revelada");

        this.texto[posicao]= letra;
    }

    public boolean isAindaComTracinhos ()
    {
        // percorre o vetor de char this.texto e verifica
        // se o mesmo ainda contem algum underline ou se ja
        // foram todos substituidos por letras; retornar true
        // caso ainda reste algum underline, ou false caso
        // contrario
        boolean tr= false;

        for(int i= 0; i <this.texto.length;i++)
            if(this.texto[i]=='_')
                tr= true;

        return tr;
    }

    @Override
    public String toString ()
    {
        // retorna um String com TODOS os caracteres que há
        // no vetor this.texto, intercalados com espaços em
        // branco
        String txt= "";

        for(int i= 0; i< this.texto.length; i++)
            txt += this.texto[i] + " ";

        return txt;
    }

    @Override
    public boolean equals (Object obj)
    {
        // verificar se this e obj possuem o mesmo conteúdo, retornando
        // true no caso afirmativo ou false no caso negativo
        if(this == obj) return true;

        if(obj == null) return false;

        if(this.getClass()!=obj.getClass()) return false;

        Tracinhos tr = (Tracinhos)obj;

        for(int i= 0; i < this.texto.length;i++)
            if(this.texto[i] != tr.texto[i])
                return false;

        return true;

    }

    @Override
    public int hashCode ()
    {
        // calcular e retornar o hashcode de this
        int n= 29;

        if(this.texto != null)
            n= n*11 + this.texto.hashCode();

        if(n<0) n= -n;

        return n;
    }

    public Tracinhos (Tracinhos t) throws Exception // construtor de cópia
    {
        // intanciar this.texto um vetor com o mesmo tamanho de t.texto
        // e compilar o conteúdo de t.texto para this.texto
        if(t==null)
            throw new Exception("Tracinhos ausente");

        this.texto= new char[t.texto.length];

        for(int i= 0; i<t.texto.length; i++)
            this.texto[i]= t.texto[i];
    }

    @Override
    public Object clone ()
    {
        // retornar uma copia de this
        Tracinhos t= null;

        try{
            t = new Tracinhos(this);

        }catch(Exception e){}

        return t;
    }
}
