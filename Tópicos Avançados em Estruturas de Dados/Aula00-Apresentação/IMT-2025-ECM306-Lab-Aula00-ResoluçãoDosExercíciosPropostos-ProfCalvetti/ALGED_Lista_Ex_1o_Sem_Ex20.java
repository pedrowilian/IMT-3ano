/*
 * ALGED - Lista Ex - 1o Sem - Ex20
 *
 *	Navegador de vetor - Métodos com passagem de parâmetros
 */
 
import	javax.swing.*;	

public class ALGED_Lista_Ex_1o_Sem_Ex20
{	
	public static void main(String Args[])
	{
		//Declaração do vetor local
		int		TAM=10;	//<---------------------------Alterar aqui o tamanho do vetor
		double	dVetor[]=new double[TAM];
		//Declaração das variáveis locais
		String	sIO,sTitle;
				
		//Tela inicial
		sTitle="ALGED-Ex20";
		sIO="Navegador de Vetor - Métodos com passagem de parâmetros";
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.PLAIN_MESSAGE);

		//Chamada dos métodos
		Digita_Vetor(dVetor);
		Navega_Vetor(dVetor);
		
		//Termina execução
      System.exit(0);
   }

	public static void Digita_Vetor(double dVet[])
	{	
		String	sTitle,sIO;
		int		iI;
		
		sTitle="Vetor - Digite";
		for(iI=0;iI<dVet.length;iI++)
		{
			sIO="Elemento para o índice "+iI+":";
			dVet[iI]=Double.parseDouble(JOptionPane.showInputDialog(null,sIO,sTitle,JOptionPane.QUESTION_MESSAGE));
		}
	}
	
	public static void Navega_Vetor(double dVet[])
	{
		String	sTitle,sIO;
		char		cOpcao;
		int		iI=0;
		
		sTitle="Vetor - Navega";
		do
		{
			sIO="Vetor["+iI+"]="+dVet[iI];
			sIO+="\n\nDigite '+' para avançar, '-' para retroceder ou '.' para sair:";
			sIO=JOptionPane.showInputDialog(null,sIO,sTitle,JOptionPane.QUESTION_MESSAGE);
			cOpcao=sIO.charAt(sIO.length()-1);
			switch(cOpcao)
			{
				case	'+':	if(iI<dVet.length-1)	iI++;
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
