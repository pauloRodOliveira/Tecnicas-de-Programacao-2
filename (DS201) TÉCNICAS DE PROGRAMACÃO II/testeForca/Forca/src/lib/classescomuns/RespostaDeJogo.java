package lib.classescomuns;
import lib.classesservidor.*;

public class RespostaDeJogo extends Comunicado
{
    private String resultRespostaDeJogo;

    public RespostaDeJogo (Tracinhos tracinhos, String frase){
        this.resultRespostaDeJogo = ("\nPalavra  :" + tracinhos +
                                     "\n"+ frase  +"\n");
    }

    public String getResultRespostaDeJogo(){
        return this.resultRespostaDeJogo;
    }

    @Override
    public String toString(){
        return("" + this.resultRespostaDeJogo);
    }
}