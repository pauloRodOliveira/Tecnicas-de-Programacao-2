package lib.classescomuns;

public class PedidoDeOperacao  extends Comunicado
{
    // guardar a operação, se é chute ou resposta;
    // guardar a tentativa.

    private char operacao;   // [C]hute ou [R]esposta
    private String tenta;    // palavra ou letra?

    public PedidoDeOperacao(char operacao, String tenta){
        this.operacao = operacao;
        this.tenta = tenta;
    }

    public char getOperacao(){
        return this.operacao;
    }

    public String getTenta(){
        return this.tenta;
    }

    public String toString(){
        return("" + this.operacao + this.tenta);
    }
}
