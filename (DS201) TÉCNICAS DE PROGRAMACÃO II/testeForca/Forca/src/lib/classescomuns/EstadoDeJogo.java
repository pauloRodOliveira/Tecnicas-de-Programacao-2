package lib.classescomuns;
import java.util.ResourceBundle.Control;

import lib.classesservidor.*;

public class EstadoDeJogo extends Comunicado
{
    private String resultEstadoDoJogo;
    private ControladorDeLetrasJaDigitadas controladorDeLetrasJaDigitadas;

    public EstadoDeJogo (Tracinhos tracinhos, ControladorDeLetrasJaDigitadas controladorDeLetrasJaDigitadas){
        this.resultEstadoDoJogo = ("\nPalavra  :" + tracinhos +
                                   "\nDigitadas:"+ controladorDeLetrasJaDigitadas + "\n");
        this.controladorDeLetrasJaDigitadas = controladorDeLetrasJaDigitadas;
    }

    public String getResultEstadoDoJogo(){
        return this.resultEstadoDoJogo;
    }

    public Boolean isJaDigitada(char letra){
        return this.controladorDeLetrasJaDigitadas.isJaDigitada(letra);
    }

    @Override
    public String toString(){
        return("" + this.resultEstadoDoJogo);
    }
}