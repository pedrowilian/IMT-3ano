/*
 * ALGED - Lista Ex - 1o Sem - Ex04
 *
 *	Inicializa��o de vetor caracteres do 'A' ao 'Z' e troca m�tua dos �ndices pares pelos �mpares
 */
 
import	javax.swing.*;	

public class ALGED_Lista_Ex_1o_Sem_Ex04
{	//Abre classe
	public static void main(String Args[])
	{	//Abre m�todo main
			
		//Defini�ao do tamanho do vetor
		int	TAM=26;	//	<---------------------- Altere aqui o tamanho do vetor e compile posteriormente

		//Declara��o das vari�veis
		String	sIO,sTitle;
		int	iI;
		char	cVetor[] = new char[TAM];

		//Tela inicial
		sIO="Inicializa��o de vetor com caracteres de 'A' ao 'Z'\ncom a troca de posi��es entre �ndices pares e �mpares";
		sTitle="ALGED-Ex04";
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.PLAIN_MESSAGE);

		//Carrega vetor com os caracteres automaticamente - Princ�pio da Coer��o
      for(iI=0;iI<TAM;iI++)
			cVetor[iI]=(char) ('A'+iI);

		//Troca de conte�dos entre �ndices pares e �mpares, sem se utilizar da 3a. vari�vel para c�pia
		for(iI=0;iI<TAM;iI+=2)
		{
			cVetor[iI]=(char) (cVetor[iI+1]-cVetor[iI]);
			cVetor[iI+1]=(char) (cVetor[iI+1]-cVetor[iI]);
			cVetor[iI]=(char) (cVetor[iI+1]+cVetor[iI]);
		}
		
		//Prepara apresenta��o dos resultados 
		sIO="Conte�do do Vetor:\n";
		sTitle="Resultado";
      for(iI=0;iI<TAM;iI++)
			if(iI%10==0)	sIO+="\n"+cVetor[iI];
			else	sIO+="  "+cVetor[iI];

		//Exibe resultado
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.INFORMATION_MESSAGE);
		
		//Termina execu��o
      System.exit(0);

   }	//Fecha m�todo main
}	//Fecha classe
