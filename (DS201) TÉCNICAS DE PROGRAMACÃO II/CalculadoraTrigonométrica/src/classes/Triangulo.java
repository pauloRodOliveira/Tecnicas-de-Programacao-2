package classes;

public class Triangulo {
    protected int lado1, lado2, lado3;
    protected byte qtdLados;

    public Triangulo(int lado1, int lado2, int lado3) throws Exception{
        /*
        * | b - c | < a < b + c
        * | a - c | < b < a + c
        * | a - b | < c < a + b
        * */
        if(lado1 <= 0 || lado2 <= 0 || lado3 <= 0) throw new Exception("O lado não pode ser menor que zero e não pode ser nulo. ");
        if(lado1 == lado2 || lado2 == lado3 || lado3 == lado1) throw new Exception("Os lados não podem ser iguais");

        int sub1 = (lado2 - lado3);
        int sub2 = (lado1 - lado3);
        int sub3 = (lado1 - lado2);
        if (sub1 < 0 || sub2 < 0 || sub3 < 0){
            sub1 = (lado2 - lado3) * (-1);
            sub2 = (lado1 - lado3) * (-1);
            sub3 = (lado1 - lado2) * (-1);
        }

        if(!(sub1 < lado1 || lado1 < (lado2 + lado3))){
            throw new Exception("Seu Triângulo não existe. ");
        }

        if(!(sub2 < lado2 || lado2 < (lado1 + lado3))){
            throw new Exception("Seu Triângulo não existe. ");
        }

        if(!(sub3 < lado3 || lado3 < (lado1 + lado2))){
            throw new Exception("Seu Triângulo não existe. ");
        }

        this.lado1 = lado1;
        this.lado2 = lado2;
        this.lado3 = lado3;
        this.qtdLados = 3;
    }

    public Triangulo(int lado1, int lado2) throws Exception{
        /*
         * | b - c | < a < b + c
         * | a - c | < b < a + c
         * | a - b | < c < a + b
         * */
        if(lado1 <= 0 || lado2 <= 0) throw new Exception("O lado não pode ser menor que zero e não pode ser nulo. ");
        if(lado1 == lado2) throw new Exception("Os lados não podem ser iguais");

        int sub3 = (lado1 - lado2);

        if(!(sub3 < lado3 || lado3 < (lado1 + lado2))){
            throw new Exception("Seu Triângulo não existe. ");
        }

        this.lado1 = lado1;
        this.lado2 = lado2;
        this.qtdLados = 2;
    }

    public int getMaiorLado(){
        int maiorLado = 0;

        if(lado1 > lado2 && lado1 > lado3)
            maiorLado = lado1;

        if(lado2 > lado1 && lado2 > lado3)
            maiorLado = lado2;

        if(lado3 > lado1 && lado3 > lado2)
            maiorLado = lado3;

        return maiorLado;
    }

    public int getMenorLado(){
        int menorLado = 0;

        if(lado1 < lado2 && lado1 < lado3)
            menorLado = lado1;

        if(lado2 < lado1 && lado2 < lado3)
            menorLado = lado2;

        if(lado3 < lado1 && lado3 < lado2)
            menorLado = lado3;

        return menorLado;
    }

    public int getLadoDoMeio(){
        int LadoDoMeio = 0;
        if(this.getMaiorLado() == 0) return 0;

        if((this.getMenorLado() == lado3 || this.getMenorLado() == lado2) &&
                (this.getMaiorLado() == lado3 || this.getMaiorLado() == lado2))
            LadoDoMeio = lado1;

        if((this.getMenorLado() == lado1 || this.getMenorLado() == lado3) &&
                (this.getMaiorLado() == lado1 || this.getMaiorLado() == lado3))
            LadoDoMeio = lado2;

        if((this.getMenorLado() == lado1 || this.getMenorLado() == lado2) &&
                (this.getMaiorLado() == lado1 || this.getMaiorLado() == lado2))
            LadoDoMeio = lado3;

        return LadoDoMeio;
    }


    @Override
    public String toString() {
        return "Triangulo{" +
                "lado1=" + lado1 +
                ", lado2=" + lado2 +
                ", lado3=" + lado3 +
                '}';
    }
}
