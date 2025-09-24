/*
 * ALGED - Lista Ex - 1o Sem - Ex25
 *
 *	Busca linear em um vetor - Recursividade
 */
 
import	javax.swing.*;	

public class ALGED_Lista_Ex_1o_Sem_Ex25
{	
	public static void main(String Args[])
	{
		//Declaração do vetor local
		int	iTAM=10;		// <----------------- Altere somente aqui o tamanho do Vetor 
		float	fVetor[]=new	float[iTAM];
		
		//Declaração das variáveis locais
		String	sIO,sTitle;
		int	iÍndice;
		float	fNúmero;
				
		//Tela inicial
		sTitle="ALGED-Ex25";
		sIO="Busca linear em um vetor - Recursividade";
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.PLAIN_MESSAGE);
	
		//Recebe vetor
		sTitle="Busca Linear Recursiva - Entrada";
		for(iÍndice=0;iÍndice<fVetor.length;iÍndice++)
		{
			sIO="Digite o elemento do Vetor["+iÍndice+"]:";
			fVetor[iÍndice]=Float.parseFloat(JOptionPane.showInputDialog(null,sIO,sTitle,JOptionPane.QUESTION_MESSAGE));
		}
		
		//Recebe número
		sIO="Número a ser localizado no vetor:";
		fNúmero=Float.parseFloat(JOptionPane.showInputDialog(null,sIO,sTitle,JOptionPane.QUESTION_MESSAGE));

		//Localiza número no vetor
		iÍndice=Busca(fVetor,0,fVetor.length-1,fNúmero);
				
		//Apresenta resultado
		sTitle="Busca Linear Recursiva - Saída";
		sIO="O número "+fNúmero;
		if(iÍndice<fVetor.length)	sIO+=" está localizado no índice "+iÍndice+" do vetor.";
		else	sIO+=" não está localizado no vetor.";
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.INFORMATION_MESSAGE);
			
		//Termina execução
      System.exit(0);
   }//Fecha método main

	public	static	int	Busca(float fVet[],int	iII, int iIF, float	fN)
	{
		if(iII<=iIF)	if(fN!=fVet[iII])	return	Busca(fVet,iII+1,iIF,fN);
		return	iII;
	}
	
}//Fecha classe
