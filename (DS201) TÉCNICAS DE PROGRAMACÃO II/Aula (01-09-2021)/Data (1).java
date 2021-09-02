public class Data{
    private byte dia, mes;
    private short ano;
    
    public Data(byte dia, byte mes, byte ano) throws Exception
    {
        if (!Data.isValida(dia, mes, ano))
            throw new Exception ("Data invalida");
            
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }
}