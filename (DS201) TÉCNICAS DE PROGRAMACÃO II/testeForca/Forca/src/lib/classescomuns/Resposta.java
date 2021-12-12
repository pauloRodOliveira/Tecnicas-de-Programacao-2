package lib.classescomuns;
import java.util.ResourceBundle.Control;

import lib.classesservidor.*;

public class Resposta extends Comunicado
{
    private String texto;

    public Resposta(String texto){
        this.texto = texto;
    }

    public String getResposta(){
        return this.texto;
    }
}