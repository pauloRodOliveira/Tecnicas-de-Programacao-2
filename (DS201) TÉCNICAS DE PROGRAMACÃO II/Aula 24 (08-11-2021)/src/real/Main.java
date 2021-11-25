package real;

import test.*;

import java.util.ArrayList;

public class Main {
    public static final String PORTA_PADRAO = "3000";
    public static void main(String[] args) {
        if(args.length>1){
            System.err.println("Uso esperado: java Servidor [PORTA]\n");
            return;
        }

        String porta = Servidor.PORTA_PADRAO;

        if(args.length == 1) porta = args[0];

        ArrayList<Parceiro> usuarios = new ArrayList<Parceiro>();


    }
}
