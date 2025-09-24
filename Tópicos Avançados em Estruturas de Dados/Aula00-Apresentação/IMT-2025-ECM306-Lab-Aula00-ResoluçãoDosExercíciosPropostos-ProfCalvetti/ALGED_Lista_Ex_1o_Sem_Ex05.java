/*
 * ALGED - Lista Ex - 1o Sem - Ex05
 *
 *	Verifica o número de capicuas de 4 dígitos existentes no vetor digitado
 */
 
import	javax.swing.*;	

public class ALGED_Lista_Ex_1o_Sem_Ex05
{	//Abre classe
	public static void main(String Args[])
	{	//Abre método main
			
		//Definiçao do tamanho do vetor
		int	TAM=16;	//	<---------------------- Altere aqui o tamanho do vetor e compile posteriormente

		//Declaração das variáveis
		String	sIO,sTitle;
		int	iI,iN=0;
		int	iVetor[] = new int[TAM];

		//Tela inicial
		sIO="Verifica quantas capicuas de 4 dígitos existem\nem um vetor qualquer digitado";
		sTitle="ALGED-Ex05";
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.PLAIN_MESSAGE);

		//Carrega o vetor pelo teclado
      for(iI=0;iI<TAM;iI++)
		{
			sIO="Elemento do tipo int para o índice "+iI+" do vetor:";
			sTitle="Digite";
			iVetor[iI]=Integer.parseInt(JOptionPane.showInputDialog(null,sIO,sTitle,JOptionPane.QUESTION_MESSAGE));
		}

		//Verifica quantas capicuas de 4 dígitos existem no vetor digitado
		for(iI=3;iI<TAM;iI++)
			if(iVetor[iI-3]==iVetor[iI] && iVetor[iI-2]==iVetor[iI-1])
				iN++;

		//Prepara apresentação dos resultados 
		sIO="Conteúdo do Vetor:\n\n";
		sTitle="Resultado";
      for(iI=0;iI<TAM;iI++)
			sIO+=iVetor[iI]+" ";
		sIO+="\n\nNúmero de capicuas encontradas: "+iN;

		//Exibe resultado
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.INFORMATION_MESSAGE);
		
		//Termina execução
      System.exit(0);

   }	//Fecha método main
}	//Fecha classe
