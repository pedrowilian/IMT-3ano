/*
 * ALGED - Lista Ex - 1o Sem - Ex11
 *
 *	Cálculo do Delta e das Raízes de uma equação do 2o. grau - Variáveis Globais
 */
 
import	javax.swing.*;	

public class ALGED_Lista_Ex_1o_Sem_Ex11
{	//Abre classe
	//Declaração das variáveis globais
	public	static	String	sIO,sTitle;
	public	static	double	dA,dB,dC,dD,dR1,dR2;

	public static void main(String Args[])
	{	//Abre método main
						
		//Tela inicial
		sTitle="ALGED-Ex11";
		sIO="Cálculo do Delta e das Raízes de uma equação do 2o. grau - Variáveis Globais";
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.PLAIN_MESSAGE);

		//Chamada dos métodos
		Digita_a();
		Digita_b();
		Digita_c();
		Calcula_e_mostra_delta();
		Calcula_e_mostra_raíz1();
		Calcula_e_mostra_raíz2();
		//Termina execução
      System.exit(0);
   }	//Fecha método main

	public static void Digita_a()
	{	//Abre método Digita_a
		sTitle="Equação do 2o. Grau - Digite";
		sIO="Valor de a:";
		dA=Double.parseDouble(JOptionPane.showInputDialog(null,sIO,sTitle,JOptionPane.QUESTION_MESSAGE));
	}	//Fecha método Digita_a
	
	public static void Digita_b()
	{	//Abre método Digita_b
		sTitle="Equação do 2o. Grau - Digite";
		sIO="Valor de b:";
		dB=Double.parseDouble(JOptionPane.showInputDialog(null,sIO,sTitle,JOptionPane.QUESTION_MESSAGE));
	}	//Fecha método Digita_b

	public static void Digita_c()
	{	//Abre método Digita_c
		sTitle="Equação do 2o. Grau - Digite";
		sIO="Valor de c:";
		dC=Double.parseDouble(JOptionPane.showInputDialog(null,sIO,sTitle,JOptionPane.QUESTION_MESSAGE));
	}	//Fecha método Digita_c
	
	public static void Calcula_e_mostra_delta()
	{	//Abre método Calcula_delta
		dD=Math.pow(dB,2)-4*dA*dC;
		sTitle="Equação do 2o. Grau - Resultado";
		sIO="Valor do Delta: "+dD;
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.INFORMATION_MESSAGE);
	}	//Fecha método Calcula_delta
	
	public static void Calcula_e_mostra_raíz1()
	{	//Abre método Calcula_e_mostra_raíz1
		sTitle="Equação do 2o. Grau - Resultado";
		if(dD<0)	sIO="Delta < 0 - Não existe raíz real r1!";
		else
		{
			dR1=(-dB+Math.sqrt(dD))/(2*dA);
			sIO="Valor da raíz r1: "+dR1;
		}
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.INFORMATION_MESSAGE);
	}	//Fecha método Calcula_e_mostra_raíz1

	public static void Calcula_e_mostra_raíz2()
	{	//Abre método Calcula_e_mostra_raíz2
		sTitle="Equação do 2o. Grau - Resultado";
		if(dD<0)	sIO="Delta < 0 - Não existe raíz real r2!";
		else
		{
			dR2=(-dB-Math.sqrt(dD))/(2*dA);
			sIO="Valor da raíz r2: "+dR2;
		}
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.INFORMATION_MESSAGE);
	}	//Fecha método Calcula_e_mostra_raíz2
}	//Fecha classe

