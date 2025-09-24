/*
 * ALGED - Lista Ex - 1o Sem - Ex09
 *
 *	Determina se matriz � identidade
 */
 
import	javax.swing.*;	

public class ALGED_Lista_Ex_1o_Sem_Ex09
{	//Abre classe
	public	static	int	LIN=3;	//	<-- Altere aqui a quantidade de linhas da matriz e compile posteriormente
	public	static	int	COL=3;	//	<-- Altere aqui a quantidade de colunas da matriz e compile posteriormente
	public	static	int	iMatriz[][]=new int[LIN][COL];		//Declara matriz global

	public static void main(String Args[])
	{	//Abre m�todo main
			
		//Declara��o das vari�veis
		String	sIO,sTitle;
		int	iL,iC;

		//Tela inicial
		sTitle="ALGED-Ex09";
		sIO="Determina se a matriz qualquer digitada � identidade";
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.PLAIN_MESSAGE);

		//Verifica se a matriz � quadrada
		if(LIN!=COL)
		{
			//Prepara apresenta��o dos resultados 
			sTitle="Resultado";
			sIO="A Matriz n�o � quadrada, portanto n�o pode ser identidade.";
		}
		else
		{
			//Digita matriz
			sTitle="Digite";
	 	  	for(iL=0;iL<LIN;iL++)
				for(iC=0;iC<COL;iC++)
				{
					sIO="Matriz["+LIN+"]["+COL+"]\n\nElemento da Linha "+iL+" e coluna "+iC;
					iMatriz[iL][iC]=Integer.parseInt(JOptionPane.showInputDialog(null,sIO,sTitle,JOptionPane.QUESTION_MESSAGE));
				}
			
			//Verifica identidade
			sIO="A matriz abaixo � identidade:";
			for(iL=0;iL<LIN;iL++)
				for(iC=0;iC<COL;iC++)
					if((iL==iC && iMatriz[iL][iC]!=1) || (iL!=iC && iMatriz[iL][iC]!=0))
					{
						sIO="A matriz abaixo n�o � identidade:";
						break;
					}
									
			//Prepara apresenta��o dos resultados 
			sTitle="Resultado";
			sIO+="\n";
  	    	for(iL=0;iL<LIN;iL++)
			{
				sIO+="\n";
				for(iC=0;iC<COL;iC++)
					sIO+=iMatriz[iL][iC]+"  ";
			}
		}
			
		//Exibe resultado
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.INFORMATION_MESSAGE);
		
		//Termina execu��o
      System.exit(0);

   }	//Fecha m�todo main
}	//Fecha classe
