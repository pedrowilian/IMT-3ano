/*
 * ALGED - Lista Ex - 1o Sem - Ex16
 *
 *	Verifica se um número digitado é par ou ímpar - Método com passagem de parâmetros
 */
 
import	javax.swing.*;	

public class ALGED_Lista_Ex_1o_Sem_Ex16
{	
	public	static	void	main(String Args[])
	{	
		String	sIO,sTitle;
		String[]	sSN={"Sim","Não"};
		int		iSN;
	
		int		iN,iResultado;
		
		//Tela inicial
		sTitle="ALGED-Ex16";
		sIO="Verifica se um número digitado é par ou ímpar - Método com passagem de parâmetros";
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.PLAIN_MESSAGE);

		//Digitação do número a ser analisado
		sTitle="Par ou Impar - Digite";
		sIO="Número a ser verificado:";
		iN=Integer.parseInt(JOptionPane.showInputDialog(null,sIO,sTitle,JOptionPane.QUESTION_MESSAGE));
		
		//Chama o teste do número
		iResultado=Par_ou_Ímpar(iN);
		
		//Analisa resultado do teste do número
		sIO="O número digitado ("+iN+") é ";
		if(iResultado==0)	sIO+="Par";
		else	sIO+="Ímpar";
		
		//Apresenta resultado
		sTitle="Par ou Impar - Resultado";
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.INFORMATION_MESSAGE);
		
		//Termina execução
      System.exit(0);
   }
	
	public	static	int	Par_ou_Ímpar(int	iN)
	{
		return	iN%2;
	}
}
