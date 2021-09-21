package classes;

public class Horario {
    private byte hor;
    private byte min;
    private byte seg;

    public boolean isValido(byte hor, byte min, byte seg){
        if ((seg < 1 || seg >= 60) || (min < 1 || min >= 60) || (hor < 1 || hor >= 24)){
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
}
