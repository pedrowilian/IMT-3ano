package dateformat;

import java.util.Calendar;
import java.text.DateFormat;
import java.util.Date;

public class Formatando_Datas {
    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();
        c.set(2013, Calendar.FEBRUARY, 28);
        Date data = c.getTime();
        System.out.println(("Data atual sem formatação: " + data));

        DateFormat formataData = DateFormat.getDateInstance();
        System.out.println("Data atual com formatação: " + formataData.format(data));

        DateFormat hora = DateFormat.getTimeInstance();
        System.out.println("Hora Formatada: " + hora.format(data));

        DateFormat dtHora = DateFormat.getDateTimeInstance();
        System.out.println(dtHora.format(data));
    }
}
