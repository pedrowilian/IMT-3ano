/*
 * ALGED - Lista Ex - 1o Sem - Ex28
 *
 *	Multiplicaçao por somas sucessivas - Recursivo
 */
import	javax.swing.*;

public class ALGED_Lista_Ex_1o_Sem_Ex28
{
	public static void main(String Args[])
	{
		String	sIO;
		int		iN1,iN2;

		JOptionPane.showMessageDialog(null,"Cálculo da Multiplicação entre dois números positivos\na serem digitados, N1 e N2, por somas sucessivas","ALGED-Ex28",JOptionPane.PLAIN_MESSAGE);
		do
		{
			iN1=Integer.parseInt(JOptionPane.showInputDialog(null,"N1 positivo: ","Digite",JOptionPane.QUESTION_MESSAGE));
		}while(iN1<0);

		do
		{
			iN2=Integer.parseInt(JOptionPane.showInputDialog(null,"N2 positivo: ","Digite",JOptionPane.QUESTION_MESSAGE));
		}while(iN2<0);

		sIO="A Multiplicação (por somas sucessivas recursivas) de "+iN1+" com "+iN2+" é "+multiplica(iN1,iN2);
		JOptionPane.showMessageDialog(null,sIO,"Resultado",JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
	}

	public static int	multiplica(int iX, int iY)
	{
		if(iX>0)
		{
			return	iY + multiplica(iX-1,iY);
		}
		else	return	0;
	}
}
