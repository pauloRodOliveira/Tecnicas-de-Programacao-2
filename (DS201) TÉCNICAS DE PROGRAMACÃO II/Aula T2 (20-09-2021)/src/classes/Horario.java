package classes;
import java.lang.Math;

public class Horario {
    private byte hor;
    private byte min;
    private byte seg;

    public static boolean isValido(byte hor, byte min, byte seg){
        if ((seg < 0 || seg >= 60) || (min < 0|| min >= 60) || (hor < 0 || hor >= 24)){
            return false;
        }

        return true;
    }

    public Horario(byte hor, byte min, byte seg)throws Exception{
        if(!isValido(hor,min,seg)) throw new Exception("Horário inválido");

        this.hor = hor;
        this.min = min;
        this.seg = seg;
    }

    public byte getHora(){return this.hor;}

    public byte getMin(){return this.min;}

    public byte getSeg(){return this.seg;}

    public void setHora(byte hor)throws Exception{
        if(!Horario.isValido(hor, this.min, this.seg)) throw new Exception("Hora inválida");

        this.hor = hor;
    }

    public void setMin(byte min)throws  Exception{
        if(!Horario.isValido(this.hor, min, this.seg)) throw new Exception("Minuto inválido");

        this.min = min;
    }

    public void setSeg(byte seg)throws  Exception{
        if(!Horario.isValido(this.hor, this.min, seg)) throw new Exception("Segundo inválido");

        this.seg = seg;
    }

    public void setTudo(byte hor, byte min, byte seg)throws Exception{
        if(!Horario.isValido(hor, min, seg)) throw new Exception("Horário inválido");

        this.hor = hor;
        this.min = min;
        this.seg = seg;
    }

    public void adiante (int qtdSeg) throws Exception
    {
        /* alterar this.horas e/ou this.minutos e/ou this.segundos
           para o horário avançar a quantidade de segundos fornecida
           como parâmetro; lançar exceção, caso essa quantidade seja
           negativa ou zero
        */
        if(qtdSeg <= 0) throw new Exception("Quantidade inválida");

        Horario temp = new Horario (this.hor, this.min, this.seg);
        int Tseg = temp.seg + qtdSeg;
        int Tmin = temp.min;
        int Thor = temp.hor;

        if (Tseg > 59){
            Tmin += Math.round(Tseg/60);
            Tseg = Tseg%60;
            if (Tmin > 59){
                Thor += Math.round(Tmin/60);
                Tmin = Tmin%60;
                if(Thor > 24){
                    Thor = Thor%24;
                }
                temp.hor = (byte)Thor;
            }
            temp.min = (byte)Tmin;
        }
        temp.seg = (byte)Tseg;

        this.seg = temp.seg;
        this.min = temp.min;
        this.hor = temp.hor;
    }

    public void atrase (int qtdSeg) throws Exception
    {
        /* alterar this.horas e/ou this.minutos e/ou this.segundos
         para o horário retroceder a quantidade de segundos fornecida
         como parâmetro; lançar exceção, caso essa quantidade seja
         negativa ou zero
         */
        if(qtdSeg <= 0) throw new Exception("Quantidade inválida");

        Horario temp = new Horario (this.hor, this.min, this.seg);
        int Tseg = temp.seg - qtdSeg;
        int Tmin = temp.min;
        int Thor = temp.hor;

        if(Tseg < 0){
            
        }else temp.seg = (byte) Tseg;

        this.seg = temp.seg;
        this.min = temp.min;
        this.hor = temp.hor;
    }
    @Override
    public String toString(){
        return (this.hor<10?"0":"") + this.hor +":"+
                (this.min<10?"0":"") + this.min +":"+
                (this.seg<10?"0":"") + this.seg;
    }

    @Override
    public boolean equals(Object obj){
        if(this==obj) return true;

        if(obj==null) return false;

        Horario horario = (Horario)obj;

        if (this.hor!=horario.hor) return false;
        if (this.min!=horario.min) return false;
        if (this.seg!=horario.seg) return false;

        return true;
    }
}
