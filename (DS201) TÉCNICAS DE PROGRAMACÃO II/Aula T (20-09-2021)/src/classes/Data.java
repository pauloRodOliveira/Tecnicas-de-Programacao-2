package classes;

public class Data
{
    private static classes.Data Data;
    private byte  dia, mes;
    private short ano;


    public static boolean isBissexto (short ano)
    {
        if (ano<1583) // vigencia do Calendario Juliano
            if (ano%4 == 0)
                return true;
            else
                return false;


        // vigencia do Calendario Gregoriano

        if (ano%400 == 0)
            return true;

        if (ano%4==0 && ano%100!=0)
            return true;

        return false;
    }

    private static boolean isValida(byte dia, byte mes, short ano)
    {
        if (dia<1 || dia>31)
            return false;

        if (mes<1 || mes>12)
            return false;

        if (ano==0)
            return false;

        if (ano==1582 && mes==10 && dia>=5 && dia<=14)
            return false; // Bula Papal Inter Gravissimas, do Papa Gregório XIII

        if ((mes==4 || mes==6 || mes==9 || mes==11) && dia>30)
            return false;

        if (mes==2 && dia>29)
            return false;

        if (mes==2 && dia>28 && !Data.isBissexto(ano))
            return false;

        return true;
    }

    public Data(short ano){
        this.ano = ano;
    }

    public Data (byte dia, byte mes, short ano) throws Exception
    {
        if (!Data.isValida(dia,mes,ano))
            throw new Exception ("Data invalida");

        this.dia=dia;
        this.mes=mes;
        this.ano=ano;
    }

    // getters

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


    // setters

    public void setDia (byte dia) throws Exception
    {
        if (!Data.isValida(dia,this.mes,this.ano))
            throw new Exception ("Dia invalido");

        this.dia=dia;
    }

    public void setMes (byte mes) throws Exception
    {
        if (!Data.isValida(this.dia,mes,this.ano))
            throw new Exception ("Mes invalido");

        this.mes=mes;
    }

    public void setAno (short ano) throws Exception
    {
        if (!Data.isValida(this.dia,this.mes,ano))
            throw new Exception ("Ano invalido");

        this.ano=ano;
    }

    public void setTudo (byte dia, byte mes, short ano) throws Exception
    {
        if (!Data.isValida(dia,mes,ano))
            throw new Exception ("Data invalida");

        this.dia=dia;
        this.mes=mes;
        this.ano=ano;
    }

    // há validaçoes duplicadas; pense em como melhorar
    public String  getDiaAnterior () throws Exception {
        // resposta mais longa
        /*
        if (this.ano==1582 && this.mes==10 && this.dia==15)
        {
            try
            {
                ret = new Data ((byte)4,this.mes,this.ano);
            }
            catch (Exception erro1)
            {} // ignora erro por ser impossível
        }
        else if (this.ano==1 && this.mes==1 && this.dia==1)
        {
            try
            {
                ret = new Data ((byte)31,(byte)12,(short)-1);
            }
            catch (Exception erro2)
            {} // ignora erro por ser impossível
        }
        else if (Data.isValida((byte)(this.dia-1),this.mes,this.ano))
        {
            try
            {
                ret = new Data ((byte)(this.dia-1),this.mes,this.ano);
            }
            catch (Exception erro3)
            {} // ignora erro por ser impossível
        }
        else if (Data.isValida((byte)31,(byte)(this.mes-1),this.ano))
        {
            try
            {
                ret = new Data ((byte)31,(byte)(this.mes-1),this.ano);
            }
            catch (Exception erro3)
            {} // ignora erro por ser impossível
        }
        else if (Data.isValida((byte)30,(byte)(this.mes-1),this.ano))
        {
            try
            {
                ret = new Data ((byte)30,(byte)(this.mes-1),this.ano);
            }
            catch (Exception erro3)
            {} // ignora erro por ser impossível
        }
        else if (Data.isValida((byte)29,(byte)(this.mes-1),this.ano))
        {
            try
            {
                ret = new Data ((byte)29,(byte)(this.mes-1),this.ano);
            }
            catch (Exception erro3)
            {} // ignora erro por ser impossível
        }
        else if (Data.isValida((byte)28,(byte)(this.mes-1),this.ano))
        {
            try
            {
                ret = new Data ((byte)28,(byte)(this.mes-1),this.ano);
            }
            catch (Exception erro3)
            {} // ignora erro por ser impossível
        }
        else
        {
            try
            {
                ret = new Data ((byte)31,(byte)12,(short)(this.ano-1));
            }
            catch (Exception erro3)
            {} // ignora erro por ser impossível
        } */

        //  chamando um método pronto para gastar menos tempo
        var ret = new Data(this.dia, this.mes, this.ano);
        ret.tranformeSeNoDiaAnterior();
        return "Dia anterior foi: " + ret;
    }

    // há validaçoes duplicadas; pense em como melhorar
    public String getDiaSeguinte () throws Exception {
        // resposta mais longa
        /*
        if (this.ano==1582 && this.mes==10 && this.dia==4)
        {
            try
            {
                ret = new Data ((byte)15,this.mes,this.ano);
            }
            catch (Exception erro1)
            {} // ignora erro por ser impossível
        }
        else if (this.ano==-1 && this.mes==12 && this.dia==31)
        {
            try
            {
                ret = new Data ((byte)1,(byte)1,(short)1);
            }
            catch (Exception erro2)
            {} // ignora erro por ser impossível
        }
        else if (Data.isValida((byte)(this.dia+1),this.mes,this.ano))
        {
            try
            {
                ret = new Data ((byte)(this.dia+1),this.mes,this.ano);
            }
            catch (Exception erro3)
            {} // ignora erro por ser impossível
        }
        else if (Data.isValida((byte)1,(byte)(this.mes+1),this.ano))
        {
            try
            {
                ret = new Data ((byte)1,(byte)(this.mes+1),this.ano);
            }
            catch (Exception erro3)
            {} // ignora erro por ser impossível
        }
        else
        {
            try
            {
                ret = new Data ((byte)1,(byte)1,(short)(this.ano+1));
            }
            catch (Exception erro3)
            {} // ignora erro por ser impossível
        }*/

        // chamando um método pronto para gastar menos tempo
        var ret = new Data(this.dia, this.mes, this.ano);
        ret.tranformeSeNoDiaSeguinte();
        return "Dia seguinte foi: " + ret;
    }

    // pensar em chamar este metodo no getDiaAnterior
    public void tranformeSeNoDiaAnterior ()
    {
        if (this.ano==1582 && this.mes==10 && this.dia==15)
            this.dia=4;
        else if (this.ano==1 && this.mes==1 && this.dia==1)
        {
            this.dia=31;
            this.mes=12;
            this.ano=-1;
        }
        else if (Data.isValida((byte)(this.dia-1),this.mes,this.ano))
            this.dia--;
        else if (Data.isValida((byte)31,(byte)(this.mes-1),this.ano))
        {
            this.dia=31;
            this.mes--;
        }
        else if (Data.isValida((byte)30,(byte)(this.mes-1),this.ano))
        {
            this.dia=30;
            this.mes--;
        }
        else if (Data.isValida((byte)29,(byte)(this.mes-1),this.ano))
        {
            this.dia=29;
            this.mes--;
        }
        else if (Data.isValida((byte)28,(byte)(this.mes-1),this.ano))
        {
            this.dia=28;
            this.mes--;
        }
        else
        {
            this.dia=31;
            this.mes=12;
            this.ano--;
        }
    }

    // pensar em chamar este metodo no getDiaSeguinte
    public void tranformeSeNoDiaSeguinte ()
    {
        if (this.ano==1582 && this.mes==10 && this.dia==4)
            this.dia=15;
        else if (this.ano==-1 && this.mes==12 && this.dia==31)
        {
            this.dia=1;
            this.mes=1;
            this.ano=1;
        }
        else if (Data.isValida((byte)(this.dia+1),this.mes,this.ano))
            this.dia++;
        else if (Data.isValida((byte)1,(byte)(this.mes+1),this.ano))
        {
            this.dia=1;
            this.mes++;
        }
        else
        {
            this.dia=1;
            this.mes=1;
            this.ano++;
        }
    }

    @Override
    public String toString ()
    {
        return (this.dia<10?"0":"")+
                this.dia+
                "/"+
                (this.mes<10?"0":"")+
                this.mes+
                "/"+
                (this.ano>0?this.ano:(-this.ano+"a.C."));
    }

    /*
     O propósito deste metodo é comparar this com obj
     */
    @Override
    public boolean equals (Object obj)
    {
        if (this==obj) return true;

        if (obj==null) return false;

        //if (!(obj instanceof Data)) return false;
        //if (obj.getClass()!=this.getClass()) return false;
        if (obj.getClass()!=Data.class) return false;

        Data data = (Data)obj;

        if (this.dia!=data.dia) return false;
        if (this.mes!=data.mes) return false;
        if (this.ano!=data.ano) return false;

        return true;
    }
}