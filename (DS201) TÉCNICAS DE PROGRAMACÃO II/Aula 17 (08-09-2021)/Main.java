/*
aula dia 08-09-2021
*/
public class Main
{
	public static void main(String[] args) {
		try {
		    Data d1 = new Data((byte)19,(byte)1,(short)1966);
		    Data d2 = new Data((byte)19,(byte)1,(short)1966);
		    System.out.println("d1: " + d1);
		    System.out.println("d1: " + d1.toString());
		    System.out.println(d1.equals(d2));
		} catch(Exception erro) {
		    System.err.println(erro.getMessage());
		}
	}
}

