/*
 * ALGED - Lista Ex - 1o Sem - Ex18
 *
 *	Verifica se um número digitado é primo - Passagem de Parâmetros
 */
 
import	javax.swing.*;

public class ALGED_Lista_Ex_1o_Sem_Ex18
{	//Abre classe
	public static void main(String Args[])
	{	//Abre método main
		//Declaração das variáveis locais
		String	sIO,sTitle;
		int		iN,iPrimo;
						
		//Tela inicial
		sTitle="ALGED-Ex18";
		sIO="Verifica se o número digitado é primo - Passagem de Parâmetros";
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.PLAIN_MESSAGE);

		//Digita número a ser testado
		iN=Digita_N();
		
		//Verifica se número digitado é primo
		iPrimo=Primo(iN);

		//Apresenta resultado
		sTitle="Número primo - Resultado";
		sIO="O número "+iN;
		if(iPrimo==1)	sIO+=" é Primo.";
		else	sIO+=" não é Primo.";
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.INFORMATION_MESSAGE);
		
		//Termina execução
      System.exit(0);
   }	//Fecha método main

	public static int Digita_N()
	{
		String	sTitle,sIO;
		int	iN;
		
		sTitle="Número Primo - Digite";
		sIO="Valor do número (N>0):";
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
