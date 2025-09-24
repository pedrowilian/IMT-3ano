/*
 * ALGED - Lista Ex - 1o Sem - Ex12
 *
 *	Verifica se um número digitado é primo - Variáveis Globais
 */
 
import	javax.swing.*;	

public class ALGED_Lista_Ex_1o_Sem_Ex12
{	//Abre classe
	//Declaração das variáveis globais
	public	static	String	sIO,sTitle;
	public	static	int	iN;

	public static void main(String Args[])
	{	//Abre método main
						
		//Tela inicial
		sTitle="ALGED-Ex12";
		sIO="Verifica se o número digitado é primo - Variáveis Globais";
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.PLAIN_MESSAGE);

		//Chamada dos métodos
		Digita_número();
		Primo();

		//Termina execução
      System.exit(0);
   }	//Fecha método main

	public static void Digita_número()
	{	//Abre método Digita_número
		sTitle="Números primos - Digite";
		sIO="Valor de N (N>0):";
		do
		{
			iN=Integer.parseInt(JOptionPane.showInputDialog(null,sIO,sTitle,JOptionPane.QUESTION_MESSAGE));
		}while(iN<=0);
	}	//Fecha método Digita_número
	
	public static void Primo()
	{	//Abre método Primo
		int	iI;
		sTitle="Números primos - Resultado";
		sIO="O número "+iN+" é Primo.";
		for(iI=2;iI<iN;iI++)
		{
			if(iN%iI==0)
			{
				sIO="O número "+iN+" não é Primo.";
				break;
			}			
		}
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.INFORMATION_MESSAGE);
	}	//Fecha método Primo
}	//Fecha classe
