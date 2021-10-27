package testes;
import classes.*;
import static java.lang.System.*;


public class ConjuntoTest {
    public static void main(String[] args) {
        //teste do método união
        try{
            out.println("teste do método união");
            Conjunto<String> teste = new Conjunto<String>(10);
            Conjunto<String> teste1 = new Conjunto<String>(10);

            teste.inclua("A");
            teste.inclua("B");
            teste.inclua("C");
            teste1.inclua("A");
            teste1.inclua("B");
            teste1.inclua("C");
            teste1.inclua("D");
            out.println("teste: "  + teste  + "\n"+
                        "teste1: " + teste1 + "\n");
            out.println("teste1 U teste: " + teste.uniao(teste1));
        }catch (Exception erro){
            err.println("Erro em testar método união: " + erro);
        }

        //teste do método interseccao
        try{
            out.println("\nteste do método interseccao");
            Conjunto<String> A = new Conjunto<String>(10);
            Conjunto<String> B = new Conjunto<String>(10);

            A.inclua("a");
            A.inclua("d");
            A.inclua("f");
            B.inclua("a");
            B.inclua("b");
            B.inclua("c");
            B.inclua("d");
            out.println("A: "  + A  + "\n"+
                        "B: "  + B  + "\n");
            out.println("B ∩ A: " + A.inteseccao(B));
        }catch (Exception erro){
            err.println("Erro em testar método interseccao: " + erro);
        }

        try{
            out.println("\nteste do método menos");
            Conjunto<String> A = new Conjunto<String>(10);
            Conjunto<String> B = new Conjunto<String>(10);

            A.inclua("a");
            A.inclua("b");
            A.inclua("c");
            A.inclua("d");
            A.inclua("e");
            B.inclua("a");
            B.inclua("b");
            B.inclua("c");
            B.inclua("d");
            B.inclua("e");
            out.println("A: "  + A  + "\n"+
                    "B: "  + B  + "\n");
            out.println("A - B: " + A.menos(B));
        }catch (Exception erro){
            err.println("Erro em testar método menos: " + erro);
        }

        try{
            out.println("\nteste do método estaContido");
            Conjunto<String> A = new Conjunto<String>(10);
            Conjunto<String> B = new Conjunto<String>(10);

            A.inclua("a");
            A.inclua("b");
            A.inclua("c");
            A.inclua("d");
            A.inclua("e");
            A.inclua("g");
            B.inclua("a");
            B.inclua("b");
            B.inclua("c");
            B.inclua("d");
            B.inclua("e");
            B.inclua("f");
            out.println("A: "  + A  + "\n"+
                    "B: "  + B  + "\n");
            out.println("A ⊂ B: " + A.estaContido(B));
        }catch (Exception erro){
            err.println("Erro em testar método estaContido: " + erro);
        }
    }
}
