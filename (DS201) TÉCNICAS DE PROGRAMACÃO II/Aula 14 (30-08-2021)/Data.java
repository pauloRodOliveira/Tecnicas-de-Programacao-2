public class Data{
    private byte dia, mes;
    private short ano;
    
    public Data (byte dia, byte mes, short ano) throws Exception{
        if(!Data.isValida(dia,mes,ano))
        throw new Exception("Data invalida");
        
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }
    
    public static boolean is isValida (byte dia, byte mes, short ano){
        if (dia<1 || dia>31)
            return false;
        
        if (mes<1 || mes>12)
            return false;
            
        if(ano == 0)
            return false;
            
        if(ano == 1582 && mes == 10 && dia < 4 && dia <= 14)
            return false; // Bula Papal Inter Gravissimas, do Papa Gregório ExceptionInInitializerError
    
        if ((mes == 4 ||  || ||))
    }   
    
}