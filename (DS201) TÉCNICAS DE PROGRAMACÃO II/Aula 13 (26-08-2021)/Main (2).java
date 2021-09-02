/* aula assícrona do dia 26-08-2021*/

import java.io.*;

public class Main
{
    private static BufferedReader teclado = new BufferedReader (new InputStreamReader (System.in));
    
    public static String getUmString(){
        String ret = null;
        
        try{
            ret = teclado.readLine();
        }catch (IOException erro){
            // sei que não vai dar erro, portanto deixo este espaço vazio.
            // não vai dar erro por que o tipo de entrada new InputStreamReader (System.in) é
            // o teclado, ou seja, não vai ter erro. mas se eu colocasse um FileReader 
            // iria ter a possibilidade de dar erro.
        }
        
        return ret;
    }
    
    public static byte getUmByte() throws Exception{
        byte ret = (byte) 0;
        //           ^ converte o int 0 para byte.
        
        try{
            ret = Byte.parseByte (teclado.readLine());
        }catch (IOException erro) {
            
        }catch(NumberFormatException erro){
            throw new Exception ("Byte invalido!");
        }
        
        return ret;
    }
    
    public static int getUmInt() throws Exception{
        int ret =  0;
        //         ^ não precisa de conversão, pois já está em int
        
        try{
            ret = Integer.parseInt (teclado.readLine());
        }catch (IOException erro) {
            
        }catch(NumberFormatException erro){
            throw new Exception ("Int invalido!");
        }
        
        return ret;
    }
    
    public static long getUmLong () throws Exception{
        Long ret =  0L;
        //         ^ não precisa de conversão, mas se escrever 0L vai ser um 0 do tipo 
        //          do tipo long, eu posso fazer a conversão explícita também.
        
        try{
            ret = Long.parseLong (teclado.readLine());
        }catch (IOException erro) {
            // não vai dar erro
        }catch(NumberFormatException erro){
            throw new Exception ("Long invalido!");
        }
        
        return ret;
        /* O mesmo ocorre para o float, porém é NECESSÁRIO colocar no o F no final, exemlo
        0.0 (automaticamente é double) 0.0F agora é float*/
    }
    
    public char getUmChar() throws Exception{
        char ret = ' ';
        
        try{
            String str = teclado.readLine();
            
            if (str == null)
                throw new Exception("Char invalido");
                
            if (str.length() != 1)
                throw new Exception("Char invalido");
                
                ret = str.charAt(0);
                    //    ^ este método charAt(), vai selecionar a posição char da string
                    //    e retorná-la.
        }catch(IOException erro){}
        
        return ret;
    }
    
	public static void main(String[] args) {
		System.out.println("Hello World");
        System.out.print("Digite seu nome: ");
        String nome = getUmString();
        System.out.print(nome);
	}
}
