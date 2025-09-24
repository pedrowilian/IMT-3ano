/*
 * ALGED - Lista Ex - 1o Sem - Ex18
 *
 *	Verifica se um n�mero digitado � primo - Passagem de Par�metros
 */
 
import	javax.swing.*;

public class ALGED_Lista_Ex_1o_Sem_Ex18
{	//Abre classe
	public static void main(String Args[])
	{	//Abre m�todo main
		//Declara��o das vari�veis locais
		String	sIO,sTitle;
		int		iN,iPrimo;
						
		//Tela inicial
		sTitle="ALGED-Ex18";
		sIO="Verifica se o n�mero digitado � primo - Passagem de Par�metros";
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.PLAIN_MESSAGE);

		//Digita n�mero a ser testado
		iN=Digita_N();
		
		//Verifica se n�mero digitado � primo
		iPrimo=Primo(iN);

		//Apresenta resultado
		sTitle="N�mero primo - Resultado";
		sIO="O n�mero "+iN;
		if(iPrimo==1)	sIO+=" � Primo.";
		else	sIO+=" n�o � Primo.";
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.INFORMATION_MESSAGE);
		
		//Termina execu��o
      System.exit(0);
   }	//Fecha m�todo main

	public static int Digita_N()
	{
		String	sTitle,sIO;
		int	iN;
		
		sTitle="N�mero Primo - Digite";
		sIO="Valor do n�mero (N>0):";
		do
		{
			iN=Integer.parseInt(JOptionPane.showInputDialog(null,sIO,sTitle,JOptionPane.QUESTION_MESSAGE));
		}while(iN<=0);
		return	iN;
	}
	
	public static int Primo(int iN)
	{
		int	iI;
		
		for(iI=2;iI<iN;iI++)	if(iN%iI==0)	return	0;
		return	1;
	}
	
}	//Fecha classe
