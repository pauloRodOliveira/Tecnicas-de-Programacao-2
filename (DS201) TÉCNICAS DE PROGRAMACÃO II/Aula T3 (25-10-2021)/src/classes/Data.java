package classes;

public class Data implements Comparable<Data>, Cloneable
{
    private byte   dia, mes;
    private short  ano;

    private static boolean isBissexto (short ano)
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

    private static boolean isValida (byte dia, byte mes, short ano)
    {
        if (dia<1 || dia>31)
            return false;

        if (mes<1 || mes>12)
            return false;

        if (ano==0)
            return false;

        if ((mes==4 || mes==6 || mes==9 || mes==11) && dia>30)
            return false;

        if (mes==2 && dia>29)
            return false;

        if (ano==1582 && mes==10 && dia>4 && dia<=14)
            return false; // Bula Papal Inter Gravissimas, do Papa Gregório XIII

        if (dia>28 && mes==2 && !Data.isBissexto(ano))
            return false;

        return true;
    }

    public Data (byte dia, byte mes, short ano) throws Exception
    {
        if (!Data.isValida(dia,mes,ano))
           throw new Exception ("Data invalida");

        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public void setDia (byte dia) throws Exception
    {
        if (!Data.isValida(dia,this.mes,this.ano))
            throw new Exception ("Dia invalido");

        this.dia = dia;
    }

    public void setMes (byte mes) throws Exception
    {
        if (!Data.isValida(this.dia,mes,this.ano))
           throw new Exception ("Mes invalido");

        this.mes = mes;
    }

    public void setAno (short ano) throws Exception
    {
        if (!Data.isValida(this.dia,this.mes,ano))
           throw new Exception ("Ano invalido");

        this.ano = ano;
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

    /*
    // há validações duplicadas
    public Data getDiaAnterior ()
    {
        Data ret=null;

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
        }
        
        return ret;
    }
    */
    public Data getDiaAnterior ()
    {
        Data ret=null;
        
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
                ret = new Data ((byte)31,(byte)12,(byte)-1);
            }
            catch (Exception erro2)
            {} // ignora erro por ser impossível
        }
        else
        {
            try
            {
                ret = new Data ((byte)(this.dia-1),this.mes,this.ano);
            }
            catch (Exception erro3)
            {
                try
                {
                    ret = new Data ((byte)31,(byte)(this.mes-1),this.ano);
                }
                catch (Exception erro4)
                {
                    try
                    {
                        ret = new Data ((byte)30,(byte)(this.mes-1),this.ano);
                    }
                    catch (Exception erro5)
                    {
                        try
                        {
                            ret = new Data ((byte)29,(byte)(this.mes-1),this.ano);
                        }
                        catch (Exception erro6)
                        {
                            try
                            {
                                ret = new Data ((byte)28,(byte)(this.mes-1),this.ano);
                            }
                            catch (Exception erro7)
                            {
                                try
                                {
                                    ret = new Data ((byte)31,(byte)12,(byte)(this.ano-1));
                                }
                                catch (Exception erro8)
                                {}  // ignora erro por ser impossível
                            }
                        }
                    }
                }
            }
        }
        
        return ret;
    }
    //
    public Data ha (int qtdDias) throws Exception
    {
        Data referencia = (Data)this.clone();
        referencia.retroceda(qtdDias);
        return referencia;
    }

    /*
    // há validações duplicadas
    public Data getDiaSeguinte ()
    {
        Data ret=null;

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
        }
        
        return ret;
    }
    */
    public Data getDiaSeguinte ()
    {
        Data ret=null;
        
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
                ret = new Data ((byte)1,(byte)1,(byte)1);
            }
            catch (Exception erro2)
            {} // ignora erro por ser impossível
        }
        else
        {
            try
            {
                ret = new Data ((byte)(this.dia+1),this.mes,this.ano);
            }
            catch (Exception erro3)
            {
                try
                {
                    ret = new Data ((byte)1,(byte)(this.mes+1),this.ano);
                }
                catch (Exception erro4)
                {
                    try
                    {
                        ret = new Data ((byte)1,(byte)1,(byte)(this.ano+1));
                    }
                    catch (Exception erro5)
                    {} // ignora erro por ser impossível
                }
            }
        }

        return ret;
    }
    //
    public Data daquiA (int qtdDias) throws Exception
    {
        Data referencia = new Data (this); // usando construtor de cópia
        referencia.avance(qtdDias);
        return referencia;
    }

    /*
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
    */
    public void tranformeSeNoDiaAnterior ()
    {
        Data anterior = this.getDiaAnterior();
        
        this.dia=anterior.dia;
        this.mes=anterior.mes;
        this.ano=anterior.ano;
    }
    //
    public void retroceda (int qtdDias) throws Exception
    {
        if (qtdDias<=0)
            throw new Exception ("Quantidade inválida");
            
        for (int i=0; i<qtdDias; i++)
            this.tranformeSeNoDiaAnterior();
    }

    /*
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
    */
    public void tranformeSeNoDiaSeguinte ()
    {
        Data seguinte = this.getDiaSeguinte();
        
        this.dia=seguinte.dia;
        this.mes=seguinte.mes;
        this.ano=seguinte.ano;
    }
    //
    public void avance (int qtdDias) throws Exception
    {
        if (qtdDias<=0)
            throw new Exception ("Quantidade inválida");
            
        for (int i=0; i<qtdDias; i++)
            this.tranformeSeNoDiaSeguinte();
    } 

    @Override
    public String toString ()
    {
        return this.dia+"/"+this.mes+"/"+this.ano;
    }

    @Override
    public boolean equals (Object obj)
    {
        if (this==obj) return true;

        if (obj==null) return false;

      //if (!(obj instanceof Data)) return false;/
      //if (obj.getClass()!=this.getClass()) return false;
        if (obj.getClass()!=Data.class) return false;

        Data data = (Data)obj;

        if (this.dia!=data.dia) return false;
        if (this.mes!=data.mes) return false;
        if (this.ano!=data.ano) return false;

        return true;
    }

    @Override
    public int hashCode ()
    {
      //int ret=super.hashCode(); // super.hashCode() por NÃO herdar DIRETAMENTE de Object (e SIM EXPLICITAMENTE de outra classe)
        int ret=666; // um valor qualquer positivo qualquer por herdar DIRETAMENTE de Object (e NÃO EXPLICITAMENTE de outra classe)

        ret = 13*ret + new Byte  (this.dia).hashCode(); // this.dia
        ret =  3*ret + new Byte  (this.mes).hashCode(); // this.mes
        ret =  7*ret + new Short (this.ano).hashCode(); // this.ano

        if (ret<0) ret = -ret;

        return ret;
    }

    /*
    Estamos neste método desejando comparar this e data;
    deveremos retornar um valor negativo, caso this seja
    menor que data;
    deveremos retornar um valor positivo, caso this seja
    maior que data;
    deveremos retornar ZERO, caso this seja
    IGUAL à data.
    */

    @Override
    public int compareTo (Data data)
    {
        if (this.ano<data.ano) return -1;
        if (this.ano>data.ano) return  1;
        if (this.mes<data.mes) return -1966;
        if (this.mes>data.mes) return  1966;
        if (this.dia<data.dia) return -666;
        if (this.dia<data.dia) return -666;
        if (this.dia>data.dia) return  666;
        return 0;
    }

    public Data (Data modelo) throws Exception
    {
        if (modelo==null)
            throw new Exception ("Modelo ausente");

        this.dia = modelo.dia;
        this.mes = modelo.mes;
        this.ano = modelo.ano;
    }

    @Override
    public Object clone ()
    {
        Data ret=null;

        try
        {
            ret = new Data (this);
        }
        catch (Exception erro)
        {} // ignorando Exception, pois sei que não ocorrera

        return ret;
    }
}
