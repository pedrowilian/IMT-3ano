/*
 * ALGED - Lista Ex - 1o Sem - Ex02
 *
 *	Inicializa��o de vetor com 0 nos �ndices pares e com o �ndice nos �ndices �mpares
 */
 
import	javax.swing.*;	

public class ALGED_Lista_Ex_1o_Sem_Ex02
{	//Abre classe
	public static void main(String Args[])
	{	//Abre m�todo main
			
		//Defini�ao do tamanho do vetor
		int	TAM=100;	//	<---------------------- Altere aqui o tamanho do vetor e compile posteriormente

		//Declara��o das vari�veis
		String	sIO,sTitle;
		int	iI;
		int	iVetor[] = new int[TAM];

		//Tela inicial
		sIO="Inicializa�ao de vetor com 0 (zero) nos �ndices pares\ne seus �ndices nos �ndices �mpares";
		sTitle="ALGED-Ex02";
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.PLAIN_MESSAGE);

		//Limpa vetor com 0 (zero)
      for(iI=0;iI<TAM;iI++)
			if(iI%2==0)	iVetor[iI]=0;
			else	iVetor[iI]=iI;

		//Prepara apresenta��o dos resultados 
		sIO="Conte�do do Vetor:\n";
		sTitle="Resultado";
      for(iI=0;iI<TAM;iI++)
			if(iI%10==0)	sIO+="\n"+iVetor[iI];
			else	sIO+="  "+iVetor[iI];

		//Exibe resultado
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.INFORMATION_MESSAGE);
		
		//Termina execu��o
      System.exit(0);

   }	//Fecha m�todo main
}	//Fecha classe
