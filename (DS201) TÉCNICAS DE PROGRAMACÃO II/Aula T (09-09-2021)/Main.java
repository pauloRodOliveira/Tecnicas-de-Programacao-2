/*
atividade autonoma dia 09-09-2021
*/

public class Main
{
	public static void main(String[] args) {
	    try {
	        Data d1 = new Data((byte)1,(byte)4,(short)2020);
	        d1. tranformeSeNoDiaSeguinte();
	        System.out.println(d1);
	        d1.transformeSeNoDiaAnterior();
	        System.out.println(d1);
	        System.out.println(d1.getDiaSeguinte());
	        System.out.println(d1);
	        System.out.println(d1.getDiaAnterior());
	        System.out.println(d1);
	        
	    } catch(Exception e) {
	        System.out.println(e);
	    }
	
}
}
