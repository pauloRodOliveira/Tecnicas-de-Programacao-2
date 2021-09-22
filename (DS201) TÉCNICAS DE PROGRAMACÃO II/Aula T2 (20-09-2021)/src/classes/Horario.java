package classes;
import static java.lang.Math.*;

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
        // alterar this.horas e/ou this.minutos e/ou this.segundos
        // para o horário avançar a quantidade de segundos fornecida
        // como parâmetro; lançar exceção, caso essa quantidade seja
        // negativa ou zero
        if(qtdSeg <= 0) throw new Exception("Quantidade inválida");

        int seg = this.seg + qtdSeg;
        int min = this.min;
        int hor = this.hor;

        if(seg > 59){
            seg = Math.round(seg%60);
            min += Math.round(seg/60);

            if(min > 59){
                min = Math.round(min%60);
                hor += Math.round(min/60);
                if(hor > 24){
                    hor = Math.round(hor/24);
                }else this.min = (byte)min; this.seg = (byte) seg; this.hor = (byte) hor;

            }else this.min = (byte)min; this.seg = (byte) seg;

        }else this.seg = (byte)seg;
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
