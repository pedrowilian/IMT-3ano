/*
 * ALGED - Lista Ex - 1o Sem - Ex21
 *
 *	Fatorial - Recursividade
 */
 
import	javax.swing.*;	

public class ALGED_Lista_Ex_1o_Sem_Ex21
{	
	public static void main(String Args[])
	{
		//Declaração das variáveis locais
		String	sIO,sTitle;
		int	iNúmero,iFatorial;
				
		//Tela inicial
		sTitle="ALGED-Ex21";
		sIO="Cálculo do Fatorial de um número digitado - Recursividade";
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.PLAIN_MESSAGE);

		//Recebe número
		sTitle="Cálculo do Fatorial - Entrada";
		sIO="Número (N>=0):";
		do
		{
			iNúmero=Integer.parseInt(JOptionPane.showInputDialog(null,sIO,sTitle,JOptionPane.QUESTION_MESSAGE));
		}while(iNúmero<0);

		//Calcula Fatorial
		iFatorial=Fatorial(iNúmero);
				
		//Apresenta resultado
		sTitle="Cálculo do Fatorial - Saída";
		sIO="O Fatorial de "+iNúmero+" é "+iFatorial+".";
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.INFORMATION_MESSAGE);
			
		//Termina execução
      System.exit(0);
   }

	public	static	int	Fatorial(int	iN)
	{
		if(iN>0)	return	iN*Fatorial(iN-1);
		else		return	1;
	}
}
