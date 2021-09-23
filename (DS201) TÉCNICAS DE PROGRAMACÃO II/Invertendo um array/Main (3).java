/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
public class Main
{
	public static void main(String[] args) {
	    System.out.println("Vetor recebe 1,2,3,4,5,6,7,8,9 e inverte 9,8,7,6,5,4,3,2,1.");
	    
	    int []v = {1,2,3,4,5,6,7,8,9};
	            // 0,1,2,3,4,5,6,7,8 -> posição.

	    int tamanho = (v.length-1);
	        for(int i = 0, f = tamanho; i<f; i++, f--){
	            int a = v[i];
	            v[i] = v[f];
	            v[f] = a;
	    }
	    
	    for(int vet : v){
	        System.out.println("Vetor invertido: "+ vet);
	    }
		
	}
	
}
