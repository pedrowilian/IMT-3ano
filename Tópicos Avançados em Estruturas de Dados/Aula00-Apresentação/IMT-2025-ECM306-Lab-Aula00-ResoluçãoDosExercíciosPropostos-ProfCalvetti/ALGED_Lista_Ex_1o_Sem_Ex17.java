/*
 * ALGED - Lista Ex - 1o Sem - Ex17
 *
 *	Cálculo do Delta e das Raízes de uma equação do 2o. grau - Métodos com passagem de parâmetros
 */
 
import	javax.swing.*;	

public class ALGED_Lista_Ex_1o_Sem_Ex17
{	//Abre classe
	public static void main(String Args[])
	{	//Abre método main
		//Declaração das variáveis locais
		String	sIO,sTitle;
		double	dA,dB,dC,dD,dR1,dR2;

		//Tela inicial
		sTitle="ALGED-Ex17";
		sIO="Cálculo do Delta e das Raízes de uma equação do 2o. grau - Passagem de Parâmetros";
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.PLAIN_MESSAGE);

		//Digita a, b e c através das chamadas dos métodos
		dA=Digita('a');
		dB=Digita('b');
		dC=Digita('c');
		
		//Calcula Delta através da chamada do método
		dD=Delta(dA,dB,dC);
		
		//Apresenta resultado do cálculo do Delta
		sTitle="Equação do 2o. Grau - Resultado";
		sIO="Delta="+dD;
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.INFORMATION_MESSAGE);
		
		//Apresenta resultado do cálculo das Raízes
		if(dD<0)	sIO="Não existem raízes reais para Delta < 0.";
		else
		{
			dR1=Raíz(1,dA,dB,dD);
			dR2=Raíz(2,dA,dB,dD);
			sIO="Raízes R1:"+dR1+" e R2:"+dR2;			
		}
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.INFORMATION_MESSAGE);
		
		//Termina execução
      System.exit(0);

   }	//Fecha método main

	public static double Digita(char	cX)
	{
		String	sTitle,sIO;
		
		sTitle="Equação do 2o. Grau - Digite";
		sIO="Valor de "+cX+":";
		return	Double.parseDouble(JOptionPane.showInputDialog(null,sIO,sTitle,JOptionPane.QUESTION_MESSAGE));
	}

	public static double Delta(double dA, double dB, double dC)
	{
		return	Math.pow(dB,2)-4*dA*dC;
	}
	
	public static double Raíz(int	iR,double dA, double dB, double dD)
	{
		if(iR==1)	return	(-dB+Math.sqrt(dD))/(2*dA);
		else			return	(-dB-Math.sqrt(dD))/(2*dA);
	}

}	//Fecha classe

