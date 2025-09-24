/*
 * ALGED - Lista Ex - 1o Sem - Ex06
 *
 *	Limpa os elementos de uma Matriz Global com -1
 */
 
import	javax.swing.*;	

public class ALGED_Lista_Ex_1o_Sem_Ex06
{	//Abre classe
	public	static	int	LIN=5;	//	<-- Altere aqui a quantidade de linhas da matriz e compile posteriormente
	public	static	int	COL=7;	//	<-- Altere aqui a quantidade de colunas da matriz e compile posteriormente
	public	static	int	iMatriz[][] = new int[LIN][COL];	//Declara matriz global

	public static void main(String Args[])
	{	//Abre método main
			
		//Declaração das variáveis
		String	sIO,sTitle;
		int	iL,iC;

		//Tela inicial
		sIO="Limpa todos elementos de uma matriz global com -1";
		sTitle="ALGED-Ex06";
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.PLAIN_MESSAGE);

		//Limpa matriz
      for(iL=0;iL<LIN;iL++)
			for(iC=0;iC<COL;iC++)
				iMatriz[iL][iC]=-1;
				
		//Prepara apresentação dos resultados 
		sTitle="Resultado";
		sIO="Matriz limpa:\n";
      for(iL=0;iL<LIN;iL++)
		{
			sIO+="\n";
			for(iC=0;iC<COL;iC++)
				sIO+=iMatriz[iL][iC]+"  ";
		}
			
		//Exibe resultado
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.INFORMATION_MESSAGE);
		
		//Termina execução
      System.exit(0);

   }	//Fecha método main
}	//Fecha classe
