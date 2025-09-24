/*
 * ALGED - Lista Ex - 1o Sem - Ex04
 *
 *	Inicialização de vetor caracteres do 'A' ao 'Z' e troca mútua dos índices pares pelos ímpares
 */
 
import	javax.swing.*;	

public class ALGED_Lista_Ex_1o_Sem_Ex04
{	//Abre classe
	public static void main(String Args[])
	{	//Abre método main
			
		//Definiçao do tamanho do vetor
		int	TAM=26;	//	<---------------------- Altere aqui o tamanho do vetor e compile posteriormente

		//Declaração das variáveis
		String	sIO,sTitle;
		int	iI;
		char	cVetor[] = new char[TAM];

		//Tela inicial
		sIO="Inicialização de vetor com caracteres de 'A' ao 'Z'\ncom a troca de posições entre índices pares e ímpares";
		sTitle="ALGED-Ex04";
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.PLAIN_MESSAGE);

		//Carrega vetor com os caracteres automaticamente - Princípio da Coerção
      for(iI=0;iI<TAM;iI++)
			cVetor[iI]=(char) ('A'+iI);

		//Troca de conteúdos entre índices pares e ímpares, sem se utilizar da 3a. variável para cópia
		for(iI=0;iI<TAM;iI+=2)
		{
			cVetor[iI]=(char) (cVetor[iI+1]-cVetor[iI]);
			cVetor[iI+1]=(char) (cVetor[iI+1]-cVetor[iI]);
			cVetor[iI]=(char) (cVetor[iI+1]+cVetor[iI]);
		}
		
		//Prepara apresentação dos resultados 
		sIO="Conteúdo do Vetor:\n";
		sTitle="Resultado";
      for(iI=0;iI<TAM;iI++)
			if(iI%10==0)	sIO+="\n"+cVetor[iI];
			else	sIO+="  "+cVetor[iI];

		//Exibe resultado
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.INFORMATION_MESSAGE);
		
		//Termina execução
      System.exit(0);

   }	//Fecha método main
}	//Fecha classe
