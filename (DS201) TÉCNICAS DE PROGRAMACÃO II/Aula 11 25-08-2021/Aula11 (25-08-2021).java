/******************************************************************************
 
 Aula 26/08/2021
 Tipos e Classes Wrapper
 
*******************************************************************************/
public class Main
{
	public static void main(String[] args) {
	    /* os tipos de variáveis são tipos primitivos, porém temos classes chamadas de Wrapper
	       (embrulhadoras), que vão guardar esses tipos. Elas possuem métodos assim como todas 
	       as outras.
	    */
	    
	    /*
		Integer a = 3, b = 7, media;
		
		media = (a + b)/2;
		
		System.out.printf ("A media entre %d e %d é  %d\n", a, b, media);
        */
        
        // instanciar a variável é opcional.
        Integer a = new Integer(3), b = new Integer(7), media;
        media = new Integer ((a.intValue() + b. intValue()/2));
        //printf serve para entragar um valor já formatado.
        System.out.printf ("A média entre %d e %d é %d", a, b, media);
	}
}
