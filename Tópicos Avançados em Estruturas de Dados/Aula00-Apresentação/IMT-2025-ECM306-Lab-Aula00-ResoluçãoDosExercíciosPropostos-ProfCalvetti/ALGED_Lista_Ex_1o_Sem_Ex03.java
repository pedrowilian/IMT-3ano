/*
 * ALGED - Lista Ex - 1o Sem - Ex03
 *
 *	Inicializa��o de vetor caracteres do 'A' ao 'Z' sem suas digita��es
 */
 
import	javax.swing.*;	

public class ALGED_Lista_Ex_1o_Sem_Ex03
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
		sIO="Inicializa��o de vetor com caracteres de 'A' ao 'Z'\nsem que haja digita��o dos mesmos";
		sTitle="ALGED-Ex03";
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.PLAIN_MESSAGE);

		//Carrega vetor com os caracteres automaticamente - Princ�pio da Coer��o
      for(iI=0;iI<TAM;iI++)
			cVetor[iI]=(char) ('A'+iI);

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
