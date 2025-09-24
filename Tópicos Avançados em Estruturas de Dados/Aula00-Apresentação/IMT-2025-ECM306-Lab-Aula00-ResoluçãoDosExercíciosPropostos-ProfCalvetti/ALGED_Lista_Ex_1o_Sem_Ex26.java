/*
 *	ALGED - Lista Ex - 1o Sem - Ex26
 *
 *	Máximo Divisor Comum - M.D.C. - Recursivo
 */
import	javax.swing.*;

public class ALGED_Lista_Ex_1o_Sem_Ex26
{
	public static void main(String Args[])
	{
		String	sIO;
		int		iN1,iN2;

		JOptionPane.showMessageDialog(null,"Cálculo do Máximo Divisor Comum (MDC)\nentre dois números a serem digitados","ALGED-Ex26",JOptionPane.PLAIN_MESSAGE);
		do
		{
			iN1=Integer.parseInt(JOptionPane.showInputDialog(null,"N1 positivo: ","Digite",JOptionPane.QUESTION_MESSAGE));
		}while(iN1<0);

		do
		{
			iN2=Integer.parseInt(JOptionPane.showInputDialog(null,"N2 positivo: ","Digite",JOptionPane.QUESTION_MESSAGE));
		}while(iN2<0);

		sIO="O MDC (Recursivo) de "+iN1+" e "+iN2+" é "+mdc(iN1,iN2);
		JOptionPane.showMessageDialog(null,sIO,"Resultado",JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
	}

	public static int	mdc(int iX, int iY)
	{
		int	iZ;

		if(iX<iY)
		{
			iZ=iX; iX=iY; iY=iZ;
		}

		if(iY>0)
		{
			iZ=iX%iY;
			iX=iY;
			iY=iZ;
			return	mdc(iX,iY);
		}
		else	return	iX;
	}
}
