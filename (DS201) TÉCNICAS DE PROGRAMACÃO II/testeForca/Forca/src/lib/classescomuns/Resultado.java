package lib.classescomuns;

public class Resultado extends Comunicado
{
    private String resultEstadoDoJogo;

    public Resultado (String resultPedidoOperacao){
        this.resultEstadoDoJogo = resultPedidoOperacao;
    }

    public String getResultEstadoDoJogo(){
        return this.resultEstadoDoJogo;
    }

    @Override
    public String toString(){
        return("" + this.resultEstadoDoJogo);
    }
}