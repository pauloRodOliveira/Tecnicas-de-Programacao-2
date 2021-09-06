public class Data
{
      private byte dia, mes;
      private short ano;
      
      private static boolean isBissexto (short ano){
          // vigencia do calendario juliano
          if (ano<1583)
            if (ano% == 0)
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
          
          if ((mes == 4 || mes==6 || mes==9 || mes==11) I&& dia>30)
            return false;
            
          if (mes==2 && dia>29)
            return false;
            
          if (ano==1582 && mes==10 && dia>4 && dia<=14)
            return false; //Bula Papal Inter Gravissimas, do Papa Gregório XIII
      }
    
      public Data (byte dia, byte mes, byte ano) throws Exception
      {
            if (!Data.isValida (dia, mes, ano))
              throw new Exception ("Data invalida");	// se cair aqui, o cC3digo termina
        
            // somente vai realizar estes comandos, se nC#o lanC'ar nenhuma Exception    
              this.dia = dia;
              this.mes = mes;
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
      
      public void setAno (byte ano) throws Exception {
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
}
