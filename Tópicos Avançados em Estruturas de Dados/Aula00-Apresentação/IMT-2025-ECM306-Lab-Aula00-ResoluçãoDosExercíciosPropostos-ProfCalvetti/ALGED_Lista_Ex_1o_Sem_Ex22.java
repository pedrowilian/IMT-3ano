/*
 * ALGED - Lista Ex - 1o Sem - Ex22
 *
 *	S�rie de Fibonacci - Recursividade
 */
 
import	javax.swing.*;	

public class ALGED_Lista_Ex_1o_Sem_Ex22
{	
	public static void main(String Args[])
	{
		//Declara��o das vari�veis locais
		String	sIO,sTitle;
		int	iTermo,iFibonacci;
				
		//Tela inicial
		sTitle="ALGED-Ex22";
		sIO="S�rie de Fibonacci - Recursividade";
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.PLAIN_MESSAGE);

		//Recebe n�mero
		sTitle="S�rie de Fibonacci - Entrada";
		sIO="Termo (T >= 0):";
		do
		{
			iTermo=Integer.parseInt(JOptionPane.showInputDialog(null,sIO,sTitle,JOptionPane.QUESTION_MESSAGE));
		}while(iTermo<0);
		
		//Calcula
		iFibonacci=Fibonacci(iTermo);
				
		//Apresenta resultado
		sTitle="S�rie de Fibonacci - Sa�da";
		sIO="O valor da S�rie de Fibonacci para o termo "+iTermo+" � "+iFibonacci+".";
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.INFORMATION_MESSAGE);
			
		//Termina execu��o
      System.exit(0);
   }

	public	static	int	Fibonacci(int	iT)
	{
		if(iT>0)	if(iT>1)		return	Fibonacci(iT-2)+Fibonacci(iT-1);
					else			return	1;
 		else		return	0;
	}
}
