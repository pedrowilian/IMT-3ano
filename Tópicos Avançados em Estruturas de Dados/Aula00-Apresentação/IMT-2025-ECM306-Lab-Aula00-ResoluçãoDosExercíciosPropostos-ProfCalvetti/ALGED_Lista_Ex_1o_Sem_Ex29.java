/*
 * ALGED - Lista Ex - 1o Sem - Ex29
 *
 *	Subtra��o por compara��es sucessivas - Recursivo
 */
import	javax.swing.*;

public class ALGED_Lista_Ex_1o_Sem_Ex29
{
	public static void main(String Args[])
	{
		String	sIO;
		int		iN1,iN2;

		JOptionPane.showMessageDialog(null,"C�lculo da Subtra��o entre dois n�meros positivos\na serem digitados, N1 e N2, por compara��es sucessivas","ALGED-Ex29",JOptionPane.PLAIN_MESSAGE);
		do
		{
			iN1=Integer.parseInt(JOptionPane.showInputDialog(null,"N1 positivo(maior ou igual a N2): ","Digite",JOptionPane.QUESTION_MESSAGE));
		}while(iN1<0);

		do
		{
			iN2=Integer.parseInt(JOptionPane.showInputDialog(null,"N2 positivo(menor ou igual a N1): ","Digite",JOptionPane.QUESTION_MESSAGE));
		}while(iN2<0||iN2>iN1);

		sIO="A Subtra��o (por compara��es sucessivas recursivas ) de "+iN2+" em "+iN1+" d� "+subtrai(iN1,iN2);
		JOptionPane.showMessageDialog(null,sIO,"Resultado",JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
	}

	public static int	subtrai(int iX, int iY)
	{
		if(iX>iY)
		{
			return	1 + subtrai(iX,iY+1);
		}
		else	return	0;
	}
}
