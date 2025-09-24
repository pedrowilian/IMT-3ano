/*
 * ALGED - Lista Ex - 1o Sem - Ex03
 *
 *	Inicialização de vetor caracteres do 'A' ao 'Z' sem suas digitações
 */
 
import	javax.swing.*;	

public class ALGED_Lista_Ex_1o_Sem_Ex03
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
		sIO="Inicialização de vetor com caracteres de 'A' ao 'Z'\nsem que haja digitação dos mesmos";
		sTitle="ALGED-Ex03";
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.PLAIN_MESSAGE);

		//Carrega vetor com os caracteres automaticamente - Princípio da Coerção
      for(iI=0;iI<TAM;iI++)
			cVetor[iI]=(char) ('A'+iI);

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
