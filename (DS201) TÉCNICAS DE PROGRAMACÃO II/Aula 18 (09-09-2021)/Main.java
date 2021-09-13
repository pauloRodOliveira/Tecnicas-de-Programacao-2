/*
aula 10-09-2021
*/
import java.util.Vector;

public class Main
{
	public static void main(String[] args) {
	    try {
	        Data d1 = new Data((byte)19,(byte)1,(short)1966);
	        Data d2 = d1;
	        Data d3 = new Data((byte)19,(byte)1,(short)1966);
	        
	        Vector<Data> vec = new Vector<Data>();
	        vec.add(d1);
	        
	        /*o método contains() não vai funcionar da maneira adequada, pois ele
	        chama o método equals() existente no objto Object*/
	        
	        if(vec.contains(d3))
	            System.out.println("vec contains d3");
	        else
	            System.out.println("vec não contais d3");
	            
	        d1.transformeSeNoDiaSeguinte();
	        System.out.println(d1);
	        d1.transformeSeNoDiaAnterior();
	        System.out.println(d1);
	        d1.getDiaSeguinte();
	        d1.getDiaAnterior();
	        
	    } catch(Exception e) {
	    }
	}
}
