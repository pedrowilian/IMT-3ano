/*
 * ALGED - Lista Ex - 1o Sem - Ex10
 *
 *	Gerencia Lugares de um Teatro
 */
 
import	javax.swing.*;	

public class ALGED_Lista_Ex_1o_Sem_Ex10
{	//Abre classe
	public	static	int	FILA=30;			//	<- Altere aqui a quantidade de linhas da matriz e compile posteriormente
	public	static	int	CADEIRA=100;	//	<- Altere aqui a quantidade de colunas da matriz e compile posteriormente
	public	static	int	iTeatro[][]=new int[FILA][CADEIRA];		//Declara matriz global

	public static void main(String Args[])
	{	//Abre método main
			
		//Declaração das variáveis
		String	sIO,sTitle,sStatus;
		int	iF,iC,iStatus;
		char	cOpcao;
		
		//Tela inicial
		sTitle="ALGED-Ex10";
		sIO="Gerencia lugares de um teatro";
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.PLAIN_MESSAGE);

		//Limpa Teatro
		for(iF=0;iF<FILA;iF++)
			for(iC=0;iC<CADEIRA;iC++)
				iTeatro[iF][iC]=0;
				
		//Menu principal
		do
		{	
			sIO=JOptionPane.showInputDialog(null,"Controle de Assentos do Teatro\n\nA. Consulta;\nB. Reserva;\nC. Venda;\nD. Cancela;\nE. Sair\n\nDigite sua opção (A, B, C, D ou E):", "Menu principal",JOptionPane.QUESTION_MESSAGE);
			cOpcao=sIO.charAt(sIO.length()-1);
			switch(cOpcao)
			{
				case	'A':	//Consulta
				case	'a':	sTitle="Consulta";
								//Digita o número da fila
								sIO="Digite a Fila (1 a "+FILA+"):";
								do
								{
									iF=Integer.parseInt(JOptionPane.showInputDialog(null,sIO,sTitle,JOptionPane.QUESTION_MESSAGE));
								}while(iF<1 || iF>FILA);
								//Digita o número da cadeira
								sIO="Digite a Cadeira (1 a "+CADEIRA+"):";
								do
								{
									iC=Integer.parseInt(JOptionPane.showInputDialog(null,sIO,sTitle,JOptionPane.QUESTION_MESSAGE));
								}while(iC<1 || iC>CADEIRA);
								//Verifica status cadastrado do assento
								iStatus=iTeatro[iF-1][iC-1];
								if(iStatus==0)	sStatus="Livre";
								else	if(iStatus==1)	sStatus="Reservado";
										else	sStatus="Vendido";
								//Apresenta resultado da consulta
								sIO="O assento da fila "+iF+" coluna "+iC+" está "+sStatus;
								JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.INFORMATION_MESSAGE);
								break;
				case	'B':	//Reserva
				case	'b':	sTitle="Reserva";
								//Digita o número da fila
								sIO="Digite a Fila (1 a "+FILA+"):";
								do
								{
									iF=Integer.parseInt(JOptionPane.showInputDialog(null,sIO,sTitle,JOptionPane.QUESTION_MESSAGE));
								}while(iF<1 || iF>FILA);
								//Digita o número da cadeira
								sIO="Digite a Cadeira (1 a "+CADEIRA+"):";
								do
								{
									iC=Integer.parseInt(JOptionPane.showInputDialog(null,sIO,sTitle,JOptionPane.QUESTION_MESSAGE));
								}while(iC<1 || iC>CADEIRA);
								//Verifica status cadastrado do assento
								iStatus=iTeatro[iF-1][iC-1];
								if(iStatus!=0)
									sIO="Atenção!\nAssento já reservado ou vendido.\nOperação abortada!!!";
								else
								{
									iTeatro[iF-1][iC-1]=1;
									sIO="O assento da fila "+iF+" coluna "+iC+" foi reservado com sucesso.";
								}															
								//Apresenta resultado da consulta
								JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.INFORMATION_MESSAGE);
								break;
				case	'C':	//Venda
				case	'c':	sTitle="Venda";
								//Digita o número da fila
								sIO="Digite a Fila (1 a "+FILA+"):";
								do
								{
									iF=Integer.parseInt(JOptionPane.showInputDialog(null,sIO,sTitle,JOptionPane.QUESTION_MESSAGE));
								}while(iF<1 || iF>FILA);
								//Digita o número da cadeira
								sIO="Digite a Cadeira (1 a "+CADEIRA+"):";
								do
								{
									iC=Integer.parseInt(JOptionPane.showInputDialog(null,sIO,sTitle,JOptionPane.QUESTION_MESSAGE));
								}while(iC<1 || iC>CADEIRA);
								//Verifica status cadastrado do assento
								iStatus=iTeatro[iF-1][iC-1];
								if(iStatus!=0)
									sIO="Atenção!\nAssento já reservado ou vendido.\nOperação abortada!!!";
								else
								{
									iTeatro[iF-1][iC-1]=2;
									sIO="O assento da fila "+iF+" coluna "+iC+" foi vendido com sucesso.";
								}															
								//Apresenta resultado da consulta
								JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.INFORMATION_MESSAGE);
								break;
				case	'D':	//Cancela
				case	'd':	sTitle="Cancela Reserva ou Venda";
								//Digita o número da fila
								sIO="Digite a Fila (1 a "+FILA+"):";
								do
								{
									iF=Integer.parseInt(JOptionPane.showInputDialog(null,sIO,sTitle,JOptionPane.QUESTION_MESSAGE));
								}while(iF<1 || iF>FILA);
								//Digita o número da cadeira
								sIO="Digite a Cadeira (1 a "+CADEIRA+"):";
								do
								{
									iC=Integer.parseInt(JOptionPane.showInputDialog(null,sIO,sTitle,JOptionPane.QUESTION_MESSAGE));
								}while(iC<1 || iC>CADEIRA);
								//Verifica status cadastrado do assento
								iStatus=iTeatro[iF-1][iC-1];
								if(iStatus==0)
									sIO="Atenção!\nAssento livre.\nOperação abortada!!!";
								else
								{
									iTeatro[iF-1][iC-1]=0;
									sIO="O assento da fila "+iF+" coluna "+iC+" foi cancelado com sucesso.";
								}															
								//Apresenta resultado da consulta
								JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.INFORMATION_MESSAGE);
								break;
				case	'E':
				case	'e':	sIO=JOptionPane.showInputDialog(null,"Deseja realmente sair (<S>im ou <N>ão)? ","Digite",JOptionPane.QUESTION_MESSAGE);
								if(sIO.charAt(sIO.length()-1)=='N' || sIO.charAt(sIO.length()-1)=='n')	cOpcao='.';
								break;
				default:		JOptionPane.showMessageDialog(null,"Favor digitar somente A, B, C, D ou E!","Erro de digitação",JOptionPane.ERROR_MESSAGE);
								break;
			}
		}while(cOpcao!='E' && cOpcao!='e');
		//Termina execução
      System.exit(0);
   }	//Fecha método main
}	//Fecha classe
