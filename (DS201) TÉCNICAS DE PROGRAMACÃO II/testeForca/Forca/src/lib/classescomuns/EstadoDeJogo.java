package lib.classescomuns;
import lib.classesservidor.*;

public class EstadoDeJogo extends Comunicado
{
    private String resultEstadoDoJogo;

    public EstadoDeJogo (Tracinhos tracinhos, ControladorDeLetrasJaDigitadas controladorDeLetrasJaDigitadas){
        this.resultEstadoDoJogo = ("\nPalavra  :" + tracinhos +
                                   "\nDigitadas:"+ controladorDeLetrasJaDigitadas + "\n");
    }

    public String getResultEstadoDoJogo(){
        return this.resultEstadoDoJogo;
    }

    @Override
    public String toString(){
        return("" + this.resultEstadoDoJogo);
    }
}