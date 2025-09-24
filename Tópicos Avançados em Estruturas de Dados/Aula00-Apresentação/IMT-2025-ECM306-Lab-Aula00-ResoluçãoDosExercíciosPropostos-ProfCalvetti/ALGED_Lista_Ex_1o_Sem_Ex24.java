/*
 * ALGED - Lista Ex - 1o Sem - Ex24
 *
 *	Números Primos - Recursividade
 */
 
import	javax.swing.*;	

public class ALGED_Lista_Ex_1o_Sem_Ex24
{	
	public static void main(String Args[])
	{
		//Declaração das variáveis locais
		String	sIO,sTitle;
		int	iNúmero,iPrimo;
				
		//Tela inicial
		sTitle="ALGED-Ex24";
		sIO="Verifica se número digitado é ou não Primo - Recursividade";
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.PLAIN_MESSAGE);

		//Recebe número
		sTitle="Verificação de Número Primo - Entrada";
		sIO="Número (N>0):";
		do
		{
			iNúmero=Integer.parseInt(JOptionPane.showInputDialog(null,sIO,sTitle,JOptionPane.QUESTION_MESSAGE));
		}while(iNúmero<=0);

		//Verifica Primo
		iPrimo=Primo(iNúmero,iNúmero);
				
		//Apresenta resultado
		sTitle="Verificação de Número Primo - Saída";
		sIO="O número "+iNúmero;
		if(iPrimo==1)	sIO+=" é Primo.";
		else				sIO+=" não é Primo.";
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.INFORMATION_MESSAGE);
			
		//Termina execução
      System.exit(0);
   }//Fecha método main

	public	static	int	Primo(int iM, int	iN)
	{
		if(iN>2)
		{
			if(Primo(iM,iN-1)==0 || iM%(iN-1)==0)	return	0;
		}
		return	1;
	}
	
}//Fecha classe
