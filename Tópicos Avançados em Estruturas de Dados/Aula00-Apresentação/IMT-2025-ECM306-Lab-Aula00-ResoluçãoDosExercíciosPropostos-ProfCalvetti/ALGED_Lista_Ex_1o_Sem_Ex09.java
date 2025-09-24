/*
 * ALGED - Lista Ex - 1o Sem - Ex09
 *
 *	Determina se matriz é identidade
 */
 
import	javax.swing.*;	

public class ALGED_Lista_Ex_1o_Sem_Ex09
{	//Abre classe
	public	static	int	LIN=3;	//	<-- Altere aqui a quantidade de linhas da matriz e compile posteriormente
	public	static	int	COL=3;	//	<-- Altere aqui a quantidade de colunas da matriz e compile posteriormente
	public	static	int	iMatriz[][]=new int[LIN][COL];		//Declara matriz global

	public static void main(String Args[])
	{	//Abre método main
			
		//Declaração das variáveis
		String	sIO,sTitle;
		int	iL,iC;

		//Tela inicial
		sTitle="ALGED-Ex09";
		sIO="Determina se a matriz qualquer digitada é identidade";
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.PLAIN_MESSAGE);

		//Verifica se a matriz é quadrada
		if(LIN!=COL)
		{
			//Prepara apresentação dos resultados 
			sTitle="Resultado";
			sIO="A Matriz não é quadrada, portanto não pode ser identidade.";
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
			sIO="A matriz abaixo é identidade:";
			for(iL=0;iL<LIN;iL++)
				for(iC=0;iC<COL;iC++)
					if((iL==iC && iMatriz[iL][iC]!=1) || (iL!=iC && iMatriz[iL][iC]!=0))
					{
						sIO="A matriz abaixo não é identidade:";
						break;
					}
									
			//Prepara apresentação dos resultados 
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
		
		//Termina execução
      System.exit(0);

   }	//Fecha método main
}	//Fecha classe
