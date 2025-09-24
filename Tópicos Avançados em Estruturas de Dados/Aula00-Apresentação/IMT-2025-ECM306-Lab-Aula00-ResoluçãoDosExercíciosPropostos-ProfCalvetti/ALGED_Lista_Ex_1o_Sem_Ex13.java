/*
 * ALGED - Lista Ex - 1o Sem - Ex13
 *
 *	Controle de qualidade de uma empresa produtora de pregos - Variáveis Globais
 */
 
import	javax.swing.*;	

public class ALGED_Lista_Ex_1o_Sem_Ex13
{	//Abre classe
	//Declaração das variáveis globais
	public	static	int		iI,iAMC,iAMD,AMOSTRAS=10;
	public	static	String	sIO,sTitle;
	public	static	double	dC,dD,dCM=0,dDM=0,dAMC,dAMD;

	public static void main(String Args[])
	{	//Abre método main
						
		//Tela inicial
		sTitle="ALGED-Ex13";
		sIO="Controle de qualidade de uma empresa produtora de pregos - Variáveis Globais";
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.PLAIN_MESSAGE);

		//Chamada dos métodos
		for(iI=1;iI<=AMOSTRAS;iI++)
		{
			Digita_Comprimento();
			Digita_Diâmetro();
			Armazena_Amostra();
		}
		
		//Mostra médias
		Diâmetro_médio();
		Comprimento_médio();
		Maior_comprimento();
		Menor_diâmetro();
		
		//Termina execução
      System.exit(0);
   }	//Fecha método main

	public static void Digita_Comprimento()
	{	//Abre método Digita_Comprimento
		sTitle="Amostra "+iI+"/"+AMOSTRAS+" - Digite";
		sIO="Valor do Comprimento em mm (C>0):";
		do
		{
			dC=Double.parseDouble(JOptionPane.showInputDialog(null,sIO,sTitle,JOptionPane.QUESTION_MESSAGE));
		}while(dC<=0);
		dCM+=dC;
	}	//Fecha método Digita_Comprimento
	
	public static void Digita_Diâmetro()
	{	//Abre método Digita_Diâmetro
		sTitle="Amostra "+iI+"/"+AMOSTRAS+" - Digite";
		sIO="Valor do Diâmetro em mm (D>0):";
		do
		{
			dD=Double.parseDouble(JOptionPane.showInputDialog(null,sIO,sTitle,JOptionPane.QUESTION_MESSAGE));
		}while(dD<=0);
		dDM+=dD;
	}	//Fecha método Digita_Diâmetro

	public static void Diâmetro_médio()
	{	//Abre método Diâmetro_médio
		//Calculo do valor médio
		dDM/=AMOSTRAS;
		sTitle="Controle de Qualidade de Amostras - Resultado";
		sIO="Diâmetro médio em mm: "+dDM;
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.INFORMATION_MESSAGE);
	}	//Fecha método Diâmetro_médio

	public static void Comprimento_médio()
	{	//Abre método Comprimento_médio
		//Calculo do valor médio
		dCM/=AMOSTRAS;
		sTitle="Controle de Qualidade de Amostras - Resultado";
		sIO="Comprimento médio em mm: "+dCM;
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.INFORMATION_MESSAGE);
	}	//Fecha método Comprimento_médio
	
	public static void Armazena_Amostra()
	{	//Abre método Armazena_Amostra
		//Valores iniciais de diâmetro e comprimento
		if(iI==1)
		{
			iAMD=iI;
			dAMD=dD;
			iAMC=iI;
			dAMC=dC;
		}
		else
		{
			//Maior comprimento
			if(dC>dAMC)
			{
				iAMC=iI;
				dAMC=dC;
			}
			//Menor diâmetro
			if(dD<dAMD)
			{
				iAMD=iI;
				dAMD=dD;
			}
		}
	}	//Fecha método Armazena_Amostra

	public static void Maior_comprimento()
	{	//Abre método Maior_comprimento
		sTitle="Controle de Qualidade de Amostras - Resultado";
		sIO="Maior Comprimento (peça "+iAMC+"): "+dAMC;
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.INFORMATION_MESSAGE);
	}	//Fecha método Maior_comprimento

	public static void Menor_diâmetro()
	{	//Abre método Menor_diâmetro
		sTitle="Controle de Qualidade de Amostras - Resultado";
		sIO="Menor Diâmetro (peça "+iAMD+"): "+dAMD;
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.INFORMATION_MESSAGE);
	}	//Fecha método Menor_diâmetro
		
}	//Fecha classe
