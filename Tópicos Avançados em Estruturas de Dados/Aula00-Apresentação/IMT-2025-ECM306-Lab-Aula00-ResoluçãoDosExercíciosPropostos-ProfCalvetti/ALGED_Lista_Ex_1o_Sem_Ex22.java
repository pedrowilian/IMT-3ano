/*
 * ALGED - Lista Ex - 1o Sem - Ex22
 *
 *	Série de Fibonacci - Recursividade
 */
 
import	javax.swing.*;	

public class ALGED_Lista_Ex_1o_Sem_Ex22
{	
	public static void main(String Args[])
	{
		//Declaração das variáveis locais
		String	sIO,sTitle;
		int	iTermo,iFibonacci;
				
		//Tela inicial
		sTitle="ALGED-Ex22";
		sIO="Série de Fibonacci - Recursividade";
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.PLAIN_MESSAGE);

		//Recebe número
		sTitle="Série de Fibonacci - Entrada";
		sIO="Termo (T >= 0):";
		do
		{
			iTermo=Integer.parseInt(JOptionPane.showInputDialog(null,sIO,sTitle,JOptionPane.QUESTION_MESSAGE));
		}while(iTermo<0);
		
		//Calcula
		iFibonacci=Fibonacci(iTermo);
				
		//Apresenta resultado
		sTitle="Série de Fibonacci - Saída";
		sIO="O valor da Série de Fibonacci para o termo "+iTermo+" é "+iFibonacci+".";
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.INFORMATION_MESSAGE);
			
		//Termina execução
      System.exit(0);
   }

	public	static	int	Fibonacci(int	iT)
	{
		if(iT>0)	if(iT>1)		return	Fibonacci(iT-2)+Fibonacci(iT-1);
					else			return	1;
 		else		return	0;
	}
}
