package classes;

public class TrianguloRet extends Triangulo{
    private int catA, catB, hip;

    public TrianguloRet(int lad1, int lad2, int lad3) throws Exception {
        super(lad1, lad2, lad3);
        this.catA = this.getLadoDoMeio();
        this.catB = this.getMenorLado();
        this.hip  = this.getMaiorLado();
    }

    public TrianguloRet(int lad1, int lad2) throws Exception {
        super(lad1, lad2);
        this.catA = this.getLadoDoMeio();
        this.catB = this.getMenorLado();
    }

    public int Hipotenusa(){
        int cal = (int)(Math.pow(catA, 2) + Math.pow(catB, 2));
        this.hip = cal;

        return this.hip;
    }
}
