package calendar;

import java.util.Calendar;

public class DateCalendarTest {
    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();
        System.out.println("Data e hora atual: " + c.getTime());
        System.out.println("Ano: " + c.get(Calendar.YEAR));
        System.out.println("Mês: " + (c.get(Calendar.MONTH) + 1)); // Mês é zero-indexado
        System.out.println("Dia do mês: " + c.get(Calendar.DAY_OF_MONTH));

        c.set(Calendar.YEAR, 1963);
        c.set(Calendar.MONTH, Calendar.JULY); // Julho é o mês 6 (zero-indexado)
        c.set(Calendar.DAY_OF_MONTH, 20);
        System.out.println("Data alterada: " + c.getTime());
        
    }
}
