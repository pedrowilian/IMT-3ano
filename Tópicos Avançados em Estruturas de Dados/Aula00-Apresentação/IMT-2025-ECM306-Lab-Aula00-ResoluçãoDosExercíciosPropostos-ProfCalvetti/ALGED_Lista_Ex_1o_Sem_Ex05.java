/*
 * ALGED - Lista Ex - 1o Sem - Ex05
 *
 *	Verifica o n�mero de capicuas de 4 d�gitos existentes no vetor digitado
 */
 
import	javax.swing.*;	

public class ALGED_Lista_Ex_1o_Sem_Ex05
{	//Abre classe
	public static void main(String Args[])
	{	//Abre m�todo main
			
		//Defini�ao do tamanho do vetor
		int	TAM=16;	//	<---------------------- Altere aqui o tamanho do vetor e compile posteriormente

		//Declara��o das vari�veis
		String	sIO,sTitle;
		int	iI,iN=0;
		int	iVetor[] = new int[TAM];

		//Tela inicial
		sIO="Verifica quantas capicuas de 4 d�gitos existem\nem um vetor qualquer digitado";
		sTitle="ALGED-Ex05";
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.PLAIN_MESSAGE);

		//Carrega o vetor pelo teclado
      for(iI=0;iI<TAM;iI++)
		{
			sIO="Elemento do tipo int para o �ndice "+iI+" do vetor:";
			sTitle="Digite";
			iVetor[iI]=Integer.parseInt(JOptionPane.showInputDialog(null,sIO,sTitle,JOptionPane.QUESTION_MESSAGE));
		}

		//Verifica quantas capicuas de 4 d�gitos existem no vetor digitado
		for(iI=3;iI<TAM;iI++)
			if(iVetor[iI-3]==iVetor[iI] && iVetor[iI-2]==iVetor[iI-1])
				iN++;

		//Prepara apresenta��o dos resultados 
		sIO="Conte�do do Vetor:\n\n";
		sTitle="Resultado";
      for(iI=0;iI<TAM;iI++)
			sIO+=iVetor[iI]+" ";
		sIO+="\n\nN�mero de capicuas encontradas: "+iN;

		//Exibe resultado
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.INFORMATION_MESSAGE);
		
		//Termina execu��o
      System.exit(0);

   }	//Fecha m�todo main
}	//Fecha classe
