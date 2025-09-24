/*
 * ALGED - Lista Ex - 1o Sem - Ex24
 *
 *	N�meros Primos - Recursividade
 */
 
import	javax.swing.*;	

public class ALGED_Lista_Ex_1o_Sem_Ex24
{	
	public static void main(String Args[])
	{
		//Declara��o das vari�veis locais
		String	sIO,sTitle;
		int	iN�mero,iPrimo;
				
		//Tela inicial
		sTitle="ALGED-Ex24";
		sIO="Verifica se n�mero digitado � ou n�o Primo - Recursividade";
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.PLAIN_MESSAGE);

		//Recebe n�mero
		sTitle="Verifica��o de N�mero Primo - Entrada";
		sIO="N�mero (N>0):";
		do
		{
			iN�mero=Integer.parseInt(JOptionPane.showInputDialog(null,sIO,sTitle,JOptionPane.QUESTION_MESSAGE));
		}while(iN�mero<=0);

		//Verifica Primo
		iPrimo=Primo(iN�mero,iN�mero);
				
		//Apresenta resultado
		sTitle="Verifica��o de N�mero Primo - Sa�da";
		sIO="O n�mero "+iN�mero;
		if(iPrimo==1)	sIO+=" � Primo.";
		else				sIO+=" n�o � Primo.";
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.INFORMATION_MESSAGE);
			
		//Termina execu��o
      System.exit(0);
   }//Fecha m�todo main

	public	static	int	Primo(int iM, int	iN)
	{
		if(iN>2)
		{
			if(Primo(iM,iN-1)==0 || iM%(iN-1)==0)	return	0;
		}
		return	1;
	}
	
}//Fecha classe
