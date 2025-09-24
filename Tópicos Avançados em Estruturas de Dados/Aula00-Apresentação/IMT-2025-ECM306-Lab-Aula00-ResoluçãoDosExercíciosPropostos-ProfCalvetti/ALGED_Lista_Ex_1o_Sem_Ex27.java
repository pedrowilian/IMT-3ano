/*
 * ALGED - Lista Ex - 1o Sem - Ex27
 *
 *	Potenciação - Recursivo
 */
import	javax.swing.*;

public class ALGED_Lista_Ex_1o_Sem_Ex27
{
	public static void main(String Args[])
	{
		String	sIO;
		int		iN1,iN2;

		JOptionPane.showMessageDialog(null,"Cálculo da Potência entre dois números\na serem digitados N1 e N2 sendo\nZ = N1 ^ N2","ALGED-Ex27",JOptionPane.PLAIN_MESSAGE);
		do
		{
			iN1=Integer.parseInt(JOptionPane.showInputDialog(null,"N1 positivo: ","Digite",JOptionPane.QUESTION_MESSAGE));
		}while(iN1<0);

		do
		{
			iN2=Integer.parseInt(JOptionPane.showInputDialog(null,"N2 positivo: ","Digite",JOptionPane.QUESTION_MESSAGE));
		}while(iN2<0);

		sIO="A Potência (pow recursivo) de "+iN1+" elevado à "+iN2+" é "+pow(iN1,iN2);
		JOptionPane.showMessageDialog(null,sIO,"Resultado",JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
	}

	public static int	pow(int iX, int iY)
	{
		if(iY>0)
		{
			return	iX * pow(iX,iY-1);
		}
		else	return	1;
	}
}
