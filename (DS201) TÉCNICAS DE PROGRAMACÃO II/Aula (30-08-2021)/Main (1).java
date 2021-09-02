/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
public class Main
{
	public static void main(String[] args) {
		try {
		    Data x = new Data ((byte)30,(byte)8, (short)2021)
		    System.out.println (x.getDia()+"/"+x.getMes()+"/"+x.getAno());
		    
		    x.setTudo((byte)29,(byte)6, (short)1992)
		    System.out.println(x.getDia()+"/"+x.getMes()+"/"+x.getANo());
		    
		    x.setDia((byte)19);
		    x.setMes((byte)1);
		    s.setAno((short)1996);
		    System.out.println(x.getDia()+"/"+x.getMes()+"/"+x.getANo());
		} catch(Exception e) {
		    System.err.println (erro.getMessage());
		}
	}
}
