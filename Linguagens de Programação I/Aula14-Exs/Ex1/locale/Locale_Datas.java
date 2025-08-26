package locale;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Locale_Datas {
    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();
        Date data = c.getTime();
        Locale brasil = new Locale("pt", "BR");
        Locale eua = Locale.US;
        Locale italia = Locale.ITALY;

        DateFormat fb = DateFormat.getDateInstance(DateFormat.FULL, brasil);
        System.out.println("Data brasileira: " + fb.format(data));
        DateFormat sb = DateFormat.getDateInstance(DateFormat.SHORT, brasil);
        System.out.println("Data brasileira: " + sb.format(data));

        DateFormat fe = DateFormat.getDateInstance(DateFormat.FULL, eua);
        System.out.println("Data americana: " + fe.format(data));
        DateFormat se = DateFormat.getDateInstance(DateFormat.SHORT, eua);
        System.out.println("Data americana: " + se.format(data));

        DateFormat fi = DateFormat.getDateInstance(DateFormat.FULL, italia);
        System.out.println("Data italiana: " + fi.format(data));
        DateFormat si = DateFormat.getDateInstance(DateFormat.SHORT, italia);
        System.out.println("Data italiana: " + si.format(data));
    }
}
