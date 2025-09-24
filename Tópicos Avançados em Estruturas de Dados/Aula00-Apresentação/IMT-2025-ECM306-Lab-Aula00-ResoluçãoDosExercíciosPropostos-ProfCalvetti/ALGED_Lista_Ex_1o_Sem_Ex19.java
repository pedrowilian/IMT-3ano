/*
 * ALGED - Lista Ex - 1o Sem - Ex19
 *
 *	Controle de qualidade de uma empresa produtora de pregos - Passagem de Parâmetros
 */
 
import	javax.swing.*;	

public class ALGED_Lista_Ex_1o_Sem_Ex19
{	//Abre classe
	//Declaração das variáveis globais
	public	static	int		AMOSTRAS=10;

	public static void main(String Args[])
	{	//Abre método main
		//Declaração das variáveis locais
		String	sIO,sTitle;
		double	dC,dD,dCM=0,dDM=0,dAMC=0,dAMD=0;
		int		iI,iAMC=0,iAMD=0;
						
		//Tela inicial
		sTitle="ALGED-Ex19";
		sIO="Controle de qualidade de uma empresa produtora de pregos - Passagem de Parâmetros";
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.PLAIN_MESSAGE);

		//Digita amostras
		for(iI=1;iI<=AMOSTRAS;iI++)
		{
			dC=Comprimento(iI);
			dCM+=dC;
			dD=Diâmetro(iI);
			dDM+=dD;
			//Analisa valores digitados
			if(iI==1)
			{
				iAMD=iI;	dAMD=dD;	iAMC=iI;	dAMC=dC;
			}
			else
			{
				//Maior comprimento
				if(dC>dAMC)
				{
					iAMC=iI;	dAMC=dC;
				}
				//Menor diâmetro
				if(dD<dAMD)
				{
					iAMD=iI;	dAMD=dD;
				}
			}
		}
		sTitle="Controle de Qualidade de Amostras - Resultado";
		//Calculo e apresentação do valor médio
		dDM/=AMOSTRAS;
		sIO="Diâmetro médio em mm: "+dDM;
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.INFORMATION_MESSAGE);
		//Calculo e apresentação do valor médio
		dCM/=AMOSTRAS;
		sIO="Comprimento médio em mm: "+dCM;
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.INFORMATION_MESSAGE);
		//Apresentação do maior comprimento
		sIO="Maior Comprimento (peça "+iAMC+"): "+dAMC;
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.INFORMATION_MESSAGE);
		//Apresentação do menor diâmetro
		sIO="Menor Diâmetro (peça "+iAMD+"): "+dAMD;
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.INFORMATION_MESSAGE);
			
		//Termina execução
      System.exit(0);
   }	//Fecha método main

	public static double	Comprimento(int iI)
	{	
		String	sTitle,sIO;
		double	dC;
		
		sTitle="Amostra "+iI+"/"+AMOSTRAS+" - Digite";
		sIO="Valor do Comprimento em mm (C>0):";
		do
		{
			dC=Double.parseDouble(JOptionPane.showInputDialog(null,sIO,sTitle,JOptionPane.QUESTION_MESSAGE));
		}while(dC<=0);
		return	dC;
	}
		
	public static double	Diâmetro(int iI)
	{
		String	sTitle,sIO;
		double	dD;

		sTitle="Amostra "+iI+"/"+AMOSTRAS+" - Digite";
		sIO="Valor do Diâmetro em mm (D>0):";
		do
		{
			dD=Double.parseDouble(JOptionPane.showInputDialog(null,sIO,sTitle,JOptionPane.QUESTION_MESSAGE));
		}while(dD<=0);
		return	dD;
	}

}	//Fecha classe
