package com.company;
import java.io.*;
import classes.Data;
import static java.lang.System.*;

public class Main
{
    public static void main(String[] args) {
        try{
            Data dPaulo = new Data ((byte)3,(byte)5,(short)2004);
            Data dJulia = new Data ((byte)31,(byte)7,(short)2006);
            
            out.println(dPaulo + " comparado com " + dJulia + " " + dPaulo.compareTo(dJulia));
            out.println(dJulia + " comparado com " + dPaulo + " " + dJulia.compareTo(dPaulo));
        }catch (Exception erro){
            err.println("Deu errado: " + erro);
        }
    }
}
