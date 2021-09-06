/*Aula 14 - 15
dias 01-02 de setembro
*/

public class Main
{
	public static void main(String[] args) {
		
		try{
    		Data x;
    		x = new Data ((byte)30,(byte)8,(short)2021);
            System.out.println (x.getDia()+"/"+x.getMes()+"/"+x.getAno());
            
            x.setTudo((byte)29, (byte)6, (short)1992);
            System.out.println (x.getDia()+"/"+x.getMes()+"/"+x.getAno());
            
            x.setDia((byte)19);
            x.setMes((byte)1);
            x.setAno((short)1966);
            System.out.println (x.getDia()+"/"+x.getMes()+"/"+x.getAno());
		}catch(Exception erro){
		    System.err.println (erro.getMessage());
		}
	}
}
