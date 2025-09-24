/*
 * ALGED - Lista Ex - 1o Sem - Ex01
 *
 *	Inicialização de vetor com 0
 */
 
import	javax.swing.*;	

public class ALGED_Lista_Ex_1o_Sem_Ex01
{	//Abre classe
	public static void main(String Args[])
	{	//Abre método main
			
		//Definiçao do tamanho do vetor
		int	TAM=100;	//	<---------------------- Altere aqui o tamanho do vetor e compile posteriormente

		//Declaração das variáveis
		String	sIO,sTitle;
		int	iI;
		int	iVetor[] = new int[TAM];

		//Tela inicial
		sIO="Inicializaçao de vetor com 0 (zero)";
		sTitle="ALGED-Ex01";
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.PLAIN_MESSAGE);

		//Limpa vetor com 0 (zero)
      for(iI=0;iI<TAM;iI++)	iVetor[iI]=0;

		//Prepara apresentação dos resultados 
		sIO="Conteúdo do Vetor:\n";
		sTitle="Resultado";
      for(iI=0;iI<TAM;iI++)
			if(iI%10==0)	sIO+="\n";
			else	sIO+=iVetor[iI]+" ";

		//Exibe resultado
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.INFORMATION_MESSAGE);
		
		//Termina execução
      System.exit(0);

   }	//Fecha método main
}	//Fecha classe
