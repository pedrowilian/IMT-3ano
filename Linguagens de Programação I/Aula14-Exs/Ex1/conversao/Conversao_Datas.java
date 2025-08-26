package conversao;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class Conversao_Datas {
    public static void main(String[] args) throws ParseException {
        Calendar c = Calendar.getInstance();
        Date data = c.getTime();
        DateFormat f = DateFormat.getDateInstance();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Data formatada: " + sdf.format(data));

        System.out.println("Data formatada: " + sdf.parse("02/08/1970"));

    }
}
