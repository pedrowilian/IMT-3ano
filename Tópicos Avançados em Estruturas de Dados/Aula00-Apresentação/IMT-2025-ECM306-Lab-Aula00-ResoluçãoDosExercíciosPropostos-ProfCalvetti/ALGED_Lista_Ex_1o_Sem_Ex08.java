/*
 * ALGED - Lista Ex - 1o Sem - Ex08
 *
 *	Determina se matriz é simétrica
 */
 
import	javax.swing.*;	

public class ALGED_Lista_Ex_1o_Sem_Ex08
{	//Abre classe
	public	static	int	LIN=5;	//	<-- Altere aqui a quantidade de linhas da matriz e compile posteriormente
	public	static	int	COL=5;	//	<-- Altere aqui a quantidade de colunas da matriz e compile posteriormente
	public	static	int	iMatriz[][]=new int[LIN][COL];		//Declara matriz global
	public	static	int	iTransposta[][]=new int[COL][LIN]; 	//Declara matriz transposta global

	public static void main(String Args[])
	{	//Abre método main
			
		//Declaração das variáveis
		String	sIO,sTitle;
		int	iL,iC;

		//Tela inicial
		sTitle="ALGED-Ex08";
		sIO="Determina se a matriz qualquer digitada é simétrica";
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.PLAIN_MESSAGE);

		//Verifica se a matriz pode ser simétrica dado o seu tamanho
		if(LIN!=COL)
		{
			//Prepara apresentação dos resultados 
			sTitle="Resultado";
			sIO="A Matriz não é simétrica, pois os números de Linhas e Colunas são diferentes.";
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
			
			//Determina transposta
			for(iL=0;iL<LIN;iL++)
				for(iC=0;iC<COL;iC++)
					iTransposta[iC][iL]=iMatriz[iL][iC];
		
			//Verifica simétrica
			sIO="A matriz abaixo é simétrica:";
			for(iL=0;iL<LIN;iL++)
				for(iC=0;iC<COL;iC++)
					if(iMatriz[iL][iC]!=iTransposta[iL][iC])
					{
						sIO="A matriz abaixo não é simétrica:";
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
