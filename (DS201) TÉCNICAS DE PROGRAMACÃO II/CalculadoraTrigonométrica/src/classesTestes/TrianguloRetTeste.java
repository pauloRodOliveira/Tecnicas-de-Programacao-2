package classesTestes;

import classes.TrianguloRet;
import static java.lang.System.err;
import static java.lang.System.out;

public class TrianguloRetTeste {
    public static void main(String[] args) {
        // teste do contrutor
        try{
            out.println("teste do construtor");
            TrianguloRet teste = new TrianguloRet(3,1);
            out.println(teste.Hipotenusa());
        }catch (Exception erro){
            err.println("Erro no construtor: " + erro);
        }
    }
}
