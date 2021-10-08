package classes;

public class Conjunto <X>
{
    private Object[] elem; // private X[] elem;
    private int qtd=0;

    public Conjunto (int capInicial) throws Exception
    {
        if (capInicial<=0)
            throw new Exception ("Capacidade inválida");

        //this.elem = new X      [capInicial];
        this.elem = new Object [capInicial];
    }

    public Conjunto () // construtor padrão (construtor sem parâmetros)
    {
        //this.elem = new X      [10];
        this.elem = new Object [10];
    }

    // este método deve descobrir se o x está armazenado em this.elem;
    // estando, devo informar em que posição está;
    // não estando, devo informar em que posição posso incluir, caso queira incluir
    private Object[] ondeEsta (X x)
    {
        for (int i=0; i<this.qtd; i++)
            if (x.equals(this.elem[i]))
            {
                Object[] ret = {true,i};
                return ret;
            }

        Object[] ret = {false,this.qtd};
        return ret;
    }

    private void redimensioneSe (int novaCap)
    {
        //X     [] novo = new X      [novaCap];
        Object[] novo = new Object [novaCap];

        for (int i=0; i<qtd; i++)
            novo[i] = this.elem[i];

        this.elem = novo;
    }

    public void inclua (X x) throws Exception
    {
        if (x==null)
            throw new Exception ("Elemento ausente");

        Object[] onde    = this.ondeEsta(x);
        boolean  achou   = (Boolean)onde[0];
        int      posicao = (Integer)onde[1];

        if (achou)
            throw new Exception ("Elemento repetido");

        if (this.qtd==this.elem.length)
            this.redimensioneSe (2*this.elem.length);

        this.elem[posicao] = x;

        this.qtd++;
    }

    public boolean tem (X x) throws Exception
    {
        if (x==null) throw new Exception("Elemento ausente");

        Object[] onde = this.ondeEsta(x);
        boolean achou = (Boolean)onde[0];

        return achou;
    }

    public X getElemento (int i) throws Exception
    {
        if (i<0 || i>=this.qtd) throw  new Exception("Elemento inválido");

        return (X)this.elem[i];
    }

    public void remova (X x) throws Exception
    {
        if (x == null) throw new Exception("Elemento ausente");

        Object[] onde = this.ondeEsta(x);
        boolean achou = (Boolean) onde[0];
        int   posicao = (Integer) onde[1];

        if(!achou) throw new Exception("Elemento inexistente");

        for(int i = posicao; i<this.qtd-1; i++)
            this.elem[i] = this.elem[i+1];

        this.elem[this.qtd] = null;
        this.qtd--;
    }

    public Conjunto (Conjunto<X> modelo) throws Exception // construtor de cópia (construtor com um parâmetro, cujo tipo é a própria classe)
    {
        if (modelo==null)
            throw new Exception ("Modelo ausente");


    }

    // Fazer todos os métodos obrigatórios
    @Override
    public String toString ()
    {
        String ret="{";

        if (this.qtd>0)
            ret += this.elem[0];

        for (int i=1; i<this.qtd; i++)
            ret += ","+this.elem[i];

        ret += "}";

        return ret;
    }
}