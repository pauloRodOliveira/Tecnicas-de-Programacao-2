package classesTestes;

import java.io.IOException;
import classes.*;
import static java.lang.System.*;

public class TrianguloTeste {

    public static void main(String[] args) throws IOException {
        // teste do contrutor
        try{
            out.println("teste do construtor");
            Triangulo teste = new Triangulo(2,1,8);
            out.println(teste);
            out.println(teste.getMaiorLado());
            out.println(teste.getLadoDoMeio());
            out.println(teste.getMenorLado());
        }catch (Exception erro){
            err.println("Erro no construtor: " + erro);
        }

        /*// teste do contrutor
        try{
            System.out.println("teste do construtor");
        }catch (Exception erro){
            System.err.println("teste do construtor");
        }*/
    }
}

