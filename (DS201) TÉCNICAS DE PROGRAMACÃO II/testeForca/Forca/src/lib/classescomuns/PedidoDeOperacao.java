package lib.classescomuns;

public class PedidoDeOperacao  extends Comunicado
{
    // guardar a operação, se é chute ou resposta;
    // guardar a tentativa.

    private char operacao;        // [C]hute ou [R]esposta
    private String palav;    // palavra
    private char letra;           // letra

    public PedidoDeOperacao(char operacao, String palav){
        this.operacao = operacao;
        this.palav = palav;
    }

    public PedidoDeOperacao(char operacao, char letra){
        this.operacao = operacao;
        this.letra += letra;
    }

    public char getOperacao(){
        return this.operacao;
    }

    public String getPalav(){
        return this.palav;
    }

    public char getLetra(){
        return this.letra;
    }

    public String toString(){
        if(operacao == 'R') return("" + this.operacao + this.palav);
        else return("" + this.operacao + this.letra);
    }
}
