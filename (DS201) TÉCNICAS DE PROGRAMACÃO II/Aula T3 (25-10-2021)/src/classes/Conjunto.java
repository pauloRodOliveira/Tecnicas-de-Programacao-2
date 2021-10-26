package classes;

public class Conjunto <X>
{
    private Object[] elem; // private X[] elem;
    private int qtd=0;
    private int capacidadeInicial;

    public Conjunto (int capInicial) throws Exception
    {
        if (capInicial<=0)
            throw new Exception ("Capacidade inválida");

        //this.elem = new X      [capInicial];
        this.elem = new Object [capInicial];

        this.capacidadeInicial = capInicial;
    }

    public Conjunto () // construtor padrão (construtor sem parâmetros)
    {
        //this.elem = new X      [10];
        this.elem = new Object [10];

        this.capacidadeInicial = 10;
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

        for (int i=0; i<this.qtd; i++)
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

        for (int i=this.qtd-1; i>=posicao; i--)
            this.elem[i+1] = this.elem[i];

        if (x instanceof Cloneable) {
            Clonador<X> clonador = new Clonador<X>();
            this.elem[this.qtd] = clonador.clone(x);
            //this.elem[posicao] = x.clone();
        }
        else
            this.elem[posicao] = x;

        this.qtd++;
    }

    public boolean tem (X x) throws Exception
    {
        if (x==null)
            throw new Exception ("Elemento ausente");

        Object[] onde    = this.ondeEsta(x);
        boolean  achou   = (Boolean)onde[0];

        return achou;
    }

    public X getElemento (int i) throws Exception
    {
        if (i<0 || i>=this.qtd)
            throw new Exception ("Elemento invalido");

        if (this.elem[i] instanceof Cloneable) {
            Clonador<X> clonador = new Clonador<X>();
            return clonador.clone((X) this.elem[i]);
        }
        else
            return (X)this.elem[i];
    }

    public void remova (X x) throws Exception
    {
        if (x==null)
            throw new Exception ("Elemento ausente");

        Object[] onde    = this.ondeEsta(x);
        boolean  achou   = (Boolean)onde[0];
        int      posicao = (Integer)onde[1];

        if (!achou)
            throw new Exception ("Elemento inexistente");

        for (int i=posicao; i<this.qtd-1; i++)
            this.elem[i] = this.elem[i+1];

        this.qtd--;
        this.elem[this.qtd]=null;

        if (this.elem.length>this.capacidadeInicial && this.qtd<=(int)(0.25*this.elem.length))
            this.redimensioneSe ((int)(0.5*this.elem.length));
    }

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

    @Override
    public boolean equals (Object obj)
    {
        if (this==obj) return true;

        if (obj==null) return false;

        if (this.getClass()!=obj.getClass()) return false;

        Conjunto<X> conj = (Conjunto<X>)obj;

        if (this.capacidadeInicial!=conj.capacidadeInicial) return false;
        if (this.qtd              !=conj.qtd)               return false;

        //leva em conta a ordem
        //for (int i=0; i<this.qtd; i++)
        //    if (!this.elem[i].equals(conj.elem[i]))
        //        return false;

        // sem levar em conta a ordem
        for (int i=0; i<this.qtd; i++)
        {
            boolean achou = (Boolean)this.ondeEsta((X)conj.elem[i])[0];

            if (!achou)
                return false;
        }

        return true;
    }

    @Override
    public int hashCode ()
    {
        int ret=1;

        ret = 19*ret + new Integer (this.capacidadeInicial).hashCode();
        ret = 13*ret + new Integer (this.qtd              ).hashCode();

        for (int i=0; i<this.qtd; i++)
            //if (this.elem[i]!=null)
            ret = 7*ret + this.elem[i].hashCode();

        if (ret<0) ret=-ret;

        return ret;
    }

    public Conjunto (Conjunto<X> modelo) throws Exception // construtor de cópia (construtor com um parâmetro, cujo tipo é a própria classe)
    {
        if (modelo==null)
            throw new Exception ("Modelo ausente");

        this.capacidadeInicial=modelo.capacidadeInicial;
        this.qtd              =modelo.qtd;

        //this.elem = new X      [this.modelo.length];
        this.elem = new Object [modelo.elem.length];

        for (int i=0; i<this.qtd; i++)
            this.elem[i] = modelo.elem[i];
    }

    public Object clone ()
    {
        Conjunto<X> ret=null;

        try
        {
            ret = new Conjunto<X> (this);
        }
        catch (Exception erro)
        {}

        return ret;
    }

    // Atividade autonoma

    // retornar um conjunto com todos os elementos do this e mais
    // todos os elementos do conj, sem repetição de elementos
    public Conjunto<X> uniao (Conjunto<X> conj) throws Exception
    {
        if (conj == null) throw  new Exception("Conjunto ausente");
        Conjunto<X> ret = new Conjunto<X>(this.qtd + conj.qtd);

        // primeiro, vamos incluir todos os elem de this em ret
        for(int aux = 0; aux < this.qtd; aux++){
           ret.inclua((X)this.elem[aux]);
        }

        // depois vamos verificar se o elemento que vamos adicionar de conj existe em ret
        // caso exista, somente adicionar 1 no aux,
        // caso não, incluir este elemento.
        for(int aux = 0; aux < conj.qtd; aux++){
            if(!ret.tem((X) conj.elem[aux])){
                ret.inclua((X)conj.elem[aux]);
            }
        }

        return ret;
    }


    // retornar um conjunto com os elementos comuns ao this e ao conj
    // retornar um conjunto com os elementos comuns ao this e ao conj
    public Conjunto<X> inteseccao (Conjunto<X> conj) throws Exception
    {
        if (conj == null) throw  new Exception("Conjunto ausente");
        Conjunto<X> ret;
        if (conj.qtd < this.qtd)
            ret = new Conjunto<X>(conj.qtd);
        else
            ret = new Conjunto<X>(this.qtd);

        for(int aux = 0; aux < this.qtd; aux++)
            if(conj.tem((X)this.elem[aux])){
               ret.inclua((X)this.elem[aux]);
            }

        return ret;
    }

    /*
    // retornar um conjunto com todos os elementos do this que
    // não são também elementos do conj
    public Conjunto<X> menos (Conjunto<X> conj) throws Exception
    {
        //...
    }

    // retornar true se o this contem o conj, ou seja se todos
    // os elementos do conj forem também elementos do this;
    // retornar false, caso contrário
    public boolean contem (Conjunto<X> conj) throws Exception
    {

    }

    // retornar true se o conj contém o this, ou seja se todos
    // os elementos do this forem também elementos do conj;
    // retornar false, caso contrário
    public boolean estaContido (Conjunto<X> conj) throws Exception
    {
        //...
    }
    */
}