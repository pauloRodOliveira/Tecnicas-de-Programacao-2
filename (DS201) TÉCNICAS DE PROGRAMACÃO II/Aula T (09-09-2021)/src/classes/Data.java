package classes;

public class Data<mes> // compulsotiamente (obrigadoriamente) herda de Object: toString(), hashCode, equals(), etc..
{
    private byte dia, mes;
    private short ano;

    public static boolean isBissexto (short ano){
        // vigencia do calendario juliano
        if (ano<1583)
            if (ano%4 == 0)
                return true;
            else
                return false;

        //vigencia do calendario gragoriano
        if (ano%400 == 0)
            return true;

        if (ano%4==0 && ano%100!=0)
            return true;

        return false;
    }

    private static boolean isValida (byte dia, byte mes, short ano){
        if (dia<1 || dia>31)
            return false;

        if (mes<1 || mes>12)
            return false;

        if (ano == 0)
            return false;

        if ((mes == 4 || mes==6 || mes==9 || mes==11) && dia>30)
            return false;

        if (mes==2 && dia>29)
            return false;

        if (ano==1582 && mes==10 && dia>4 && dia<=14)
            return false; //Bula Papal Inter Gravissimas, do Papa Gregório XIII

        return true;
    }

    public Data(byte dia, byte mes)
    {
        this.dia = dia;
        this.mes = mes;
    }

    public Data(byte dia){
        this.dia = dia;
    }

    public Data(short ano){
        this.ano = ano;
    }

    public Data (byte dia, byte mes, short ano) throws Exception
    {
        if (!Data.isValida (dia, mes, ano))
            throw new Exception ("Data invalida");	// se cair aqui, o cC3digo termina

        // somente vai realizar estes comandos, se nC#o lanC'ar nenhuma Exception
        this.dia = (byte)dia;
        this.mes = (byte)mes;
        this.ano = (short)ano;
    }



    public byte getDia ()
    {
        return this.dia;
    }

    public byte getMes ()
    {
        return this.mes;
    }

    public short getAno ()
    {
        return this.ano;
    }

    public void setDia (byte dia) throws Exception {
        if (!Data.isValida(dia, this.mes, this.ano))
            throw new Exception("Dia invalido");

        this.dia = dia;
    }

    public void setMes (byte mes) throws Exception {
        if (!Data.isValida(this.dia, mes, this.ano))
            throw new Exception("Mes invalido");

        this.mes = mes;
    }

    public void setAno (short ano) throws Exception {
        if (!Data.isValida(this.dia, this.mes, ano))
            throw new Exception("Ano invalido");

        this.ano = ano;
    }

    public void setTudo (byte dia, byte mes, short ano) throws Exception{
        if (!Data.isValida(dia, mes, ano))
            throw new Exception("Ano invalido");

        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    @Override // o Override vai sobrescrever o método toString() na classe Object,
    //do qual a classe Data foi herdada compulsóriamente.
    public String toString(){
        return(this.dia<10?"0":"") + this.dia + "/" + (this.mes<10?"0":"") + this.mes + "/" + this.ano;
        //  ^ (dia menor que 10 ? se sim, mostre "0" :  se não, mostre "")
    }

    @Override
    // quando eu escrevo algo com @ significa que é uma anotação
    public boolean equals (Object obj){
        try {
            if(this == obj) return true;

            if(obj == null) return false;

            if(this.getClass() != obj.getClass()) return false;
            //  ^ (esse this pega a classe Data) se essa classe Data não for igual
            //  a classe do obj, return false.

            // if(obj.getClass() != Data.class) return false;
            //                            ^ faz a mesma coisa que o this.getClass()

            Data dat = (Data)obj;

            if(this == dat){ return true;}

            if(dat == null){ return false;}

            if(this.dia != dat.dia){ return false;}
            if(this.mes != dat.mes){ return false;}
            if(this.ano != dat.ano){ return false;}
        } catch(Exception e) {
            return false;
        }

        return true;
    }

    public void tranformeSeNoDiaSeguinte(){
        dia++;

        //  abril       junho       setembro   novembro
        if((mes == 4 || mes == 6 || mes == 9 || mes == 11) && dia > 30){
            mes++;
            dia = 1;
        }else if(!Data.isBissexto(this.ano) && mes == 2 && dia >= 30){
            mes++;
            dia = 1;
        }else if (Data.isBissexto(this.ano) && dia > 28 && mes == 2){
            mes++;
            dia = 1;
        }else if(dia > 31){
            mes++;
            dia = 1;
        }else if(dia ==1 && mes ==1 && ano ==1){
            dia = 31;
            mes = 12;
            ano = 1;
        }

        if(mes>12){
            mes=1;
            dia=1;
            if(ano + 1 == 0) ano = 1;
            else if(ano + 1 != 0) ano++;
        }
    }

    public void transformeSeNoDiaAnterior(){
        dia--;

        //  abril       junho       setembro   novembro
        if((mes==5 || mes==7 || mes==10 || mes==12) && dia<1){
            mes--;
            dia=30;
        }else if (mes==3 && dia<1){
            if(!Data.isBissexto(ano)) {
                mes--;
                dia=29;
            }else{
                mes--;
                dia=28;
            }
        }else if(dia<1){
            mes--;
            dia = 31;
        }else if(dia==1 && mes==1 && ano==1){
            dia = 31;
            mes = 12;
            ano = -1;
        }

        if(mes < 1){
            mes= 12;
            dia= 31;
            if(ano - 1 == 0) ano = -1;
            else if(ano - 1 != 0) ano--;
        }
    }

    public String getDiaSeguinte(){
        byte amh = dia;

        amh++;

        //  abril       junho       setembro   novembro
        if((mes == 4 || mes == 6 || mes == 9 || mes == 11) && amh > 30){
            amh = 1;
        }else if(!Data.isBissexto(this.ano) && mes == 2 && amh >= 30){
            amh = 1;
        }else if (Data.isBissexto(this.ano) && amh > 28 && mes == 2){
            amh = 1;
        }else if(amh > 31){
            amh = 1;
        }

        if(mes > 12){

            amh = 1;
        }
        return "Amanhã será dia " + amh;
    }

    public String getDiaAnterior(){
        byte ontem = dia;
        ontem--;

        //  abril       junho       setembro   novembro
        if((mes == 5 || mes == 7 || mes == 10 || mes == 12) && ontem < 1){
            ontem = 30;
        }else if (mes == 3 && ontem < 1){
            if(!Data.isBissexto(ano)) {
                ontem = 29;
            }else{
                ontem = 28;
            }
        }else if(ontem < 1){
            ontem = 31;
        }

        if(mes > 12){
            ontem = 1;
        }
        return "Ontem foi dia " + ontem;
    }

}