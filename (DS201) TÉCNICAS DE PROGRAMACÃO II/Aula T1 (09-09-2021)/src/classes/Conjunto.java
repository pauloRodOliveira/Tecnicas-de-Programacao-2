package classes;

public class Conjunto <X> {
    private Object[] elem;    //private X[] elem;
                              //^ ela não deixa criar um vetor de tipo X[]
    private int qtd = 0;


    public Conjunto(int capInicial){
        if(capInicial<=0) throw new Exception("Capacidade inválida");

        //this.elem = new X [capInicial];
        this.elem = new Object[capInicial];
    }

    public Conjunto() // construtor padrão, ele não recebe parâmetro
    {
        this.elem = new Object[capInicial];
    }

    private Object[] ondeEsta(X x) // serve para descobrir se x (x pequeno viu?) está armazenado em this.elem;
                                   // estando, devo informar em que posição está;
                                   // não estando, devo informar em que posição posso incluir;
    {
        for(int i = 0; i < this.qtd; i++){
            if(x.equals(this.elem[i])){
                Object[] ret = {true, i};
                return  ret;
            }
        }

        Object[] ret = {false, this.qtd};
        return ret;
    }

    private  void redimensioneSe(int novaCap){
        Object[] novo = new Object[novaCap];

        for(int i=0; i<qtd; i++) {
            novo[i] = this.elem[i];
        }

        this.elem = novo;
    }

    public void inclua(X x) throws Exception{
        if(x==null) throw new Exception("Elemento ausente");

        Object[] onde    = this.ondeEsta(x);
        boolean  achou   = (Boolean)onde[0];
        int      posicao = (Integer)onde[1];

        if(achou) throw new Exception("Elemento repetido");

        if(this.qtd == this.elem.length) this.redimensioneSe(2*this.elem.length);


        this.qtd++;
    }

    public boolean tem() throws Exception{
        //...
        return true;
    }

    public X getELemento(int i) throws Exception{
        return (X) this.elem;
    }

    public void remova(X x) throws Exception{

    }

    public Conjunto(Conjunto<X> conjunto)// construtor de cópia, recebe a própria classe como parâmetro
    {

    }
}
