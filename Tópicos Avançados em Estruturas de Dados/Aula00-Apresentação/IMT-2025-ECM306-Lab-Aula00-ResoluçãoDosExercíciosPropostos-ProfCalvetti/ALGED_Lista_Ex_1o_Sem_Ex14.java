/*
 * ALGED - Lista Ex - 1o Sem - Ex14
 *
 *	Navegador de vetor - Vari�veis Globais
 */
 
import	javax.swing.*;	

public class ALGED_Lista_Ex_1o_Sem_Ex14
{	
	//Declara��o dos vetores globais
	public	static	int		TAM=10;	//<---------------------------Alterar aqui o tamanho do vetor
	public	static	double	dVetor[]=new double[TAM];
	//Declara��o das vari�veis globais
	public	static	String	sIO,sTitle;

	public static void main(String Args[])
	{				
		//Tela inicial
		sTitle="ALGED-Ex14";
		sIO="Navegador de Vetor - Vari�veis Globais";
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.PLAIN_MESSAGE);

		//Chamada dos m�todos
		Digita_Vetor();
		Navega_Vetor();
		
		//Termina execu��o
      System.exit(0);
   }

	public static void Digita_Vetor()
	{	
		int	iI;
		
		sTitle="Vetor - Digite";
		for(iI=0;iI<TAM;iI++)
		{
			sIO="Elemento para o �ndice "+iI+":";
			dVetor[iI]=Double.parseDouble(JOptionPane.showInputDialog(null,sIO,sTitle,JOptionPane.QUESTION_MESSAGE));
		}
	}
	
	public static void Navega_Vetor()
	{
		char	cOpcao;
		int	iI=0;
		
		sTitle="Vetor - Navega";
		do
		{
			sIO="Vetor["+iI+"]="+dVetor[iI];
			sIO+="\n\nDigite '+' para avan�ar, '-' para retroceder ou '.' para sair:";
			sIO=JOptionPane.showInputDialog(null,sIO,sTitle,JOptionPane.QUESTION_MESSAGE);
			cOpcao=sIO.charAt(sIO.length()-1);
			switch(cOpcao)
			{
				case	'+':	if(iI<dVetor.length-1)	iI++;
								break;
				case	'-': 	if(iI>0)	iI--;
								break;
				case	'.':	sIO="Deseja realmente sair?";
								if(JOptionPane.showConfirmDialog(null,sIO,sTitle,0,1)==1)	cOpcao='N';
								break;
				default:		sIO="Favor digitar somente '+','-' ou '.'";
								JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.ERROR_MESSAGE);
								break;
			}
		}while(cOpcao!='.');
	}
}
