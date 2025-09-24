/*
 * ALGED - Lista Ex - 1o Sem - Ex15
 *
 *	Jogo ALTO-BAIXO - Variáveis Globais
 */
 
import	javax.swing.*;	

public class ALGED_Lista_Ex_1o_Sem_Ex15
{	
	//Declaração das variáveis globais
	public	static	String	sIO,sTitle;
	public	static	String[]	sSN={"Sim","Não"};
	public	static	int		iSN;
	
	public	static	String[]	sOpcao={"Número Secreto","Jogar","Resultados","Sair"};
	public	static	int		iOpcao;
	
	public	static	int		iPalpite,iSecreto=50,iTentativas=0,iRecorde=0;
		
	public static void main(String Args[])
	{				
		//Tela inicial
		sTitle="ALGED-Ex15";
		sIO="Jogo ALTO-BAIXO - Variáveis Globais";
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.PLAIN_MESSAGE);

		//Tela de menu
		do
		{
			sTitle="Jogo ALTO-BAIXO - Menu Principal";
			sIO="Selecione o botão da opção desejada:";
			iOpcao=JOptionPane.showOptionDialog(null,sIO,sTitle,1,JOptionPane.QUESTION_MESSAGE,null,sOpcao,null);
			switch(iOpcao)
			{
				case	0:	Digita_Secreto();
							break;
				case	1:	Joga();
							break;
				case	2:	Mostra_Resultado();
							break;
				case	3:	sIO="Deseja realmente sair?";
							iSN=JOptionPane.showOptionDialog(null,sIO,sTitle,1,JOptionPane.QUESTION_MESSAGE,null,sSN,null);
							if(iSN==1)	iOpcao=-1;
							break;
			}
		}while(iOpcao!=3);
				
		//Termina execução
      System.exit(0);
   }
	
	public	static	void	Digita_Secreto()
	{
		do
		{
			sTitle="Jogo ALTO-BAIXO - Entrada de Dados";
			sIO="Digite o Número Secreto (Entre 1 e 100):";
			iSecreto=Integer.parseInt(JOptionPane.showInputDialog(null,sIO,sTitle,JOptionPane.QUESTION_MESSAGE));
		}while(iSecreto<=0 || iSecreto>100);
	}
	
	public	static	void	Joga()
	{
		int	iT=0;
		do
		{
			do
			{
				sTitle="Jogo ALTO-BAIXO - Entrada de Dados";
				sIO="Digite o Seu Palpite (Entre 1 e 100, ou 0 para Sair):";
				iPalpite=Integer.parseInt(JOptionPane.showInputDialog(null,sIO,sTitle,JOptionPane.QUESTION_MESSAGE));
			}while(iPalpite<0 || iPalpite>100);
			if(iPalpite!=0)
			{
				iT++;
				if(iPalpite==iSecreto)
				{
					sIO="Acertou!";
					if(iTentativas==0)	iRecorde=iT;
					else	if(iT<iRecorde)	iRecorde=iT;
					iTentativas=iT;
				}
				else	if(iPalpite<iSecreto)	sIO="Baixo...";
						else	sIO="Alto...";
			}
			else	sIO="Jogo Abortado!";
			sTitle="Jogo ALTO-BAIXO - Resultado";
			JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.INFORMATION_MESSAGE);
		}while(iPalpite!=iSecreto && iPalpite!=0);
	}
	
	public	static	void	Mostra_Resultado()
	{
		sTitle="Jogo ALTO-BAIXO - Resultado";
		sIO="O último acerto foi realizado com "+iTentativas+" tentativas;";
		sIO+="\nO recorde de acerto foi com "+iRecorde+" tentativas.";
		JOptionPane.showMessageDialog(null,sIO,sTitle,JOptionPane.INFORMATION_MESSAGE);
	}
}
