package datatime;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateTimeTest {
    public static void main(String[] args) {
        Calendar dateTime = Calendar.getInstance();
        System.out.printf("%tc\n", dateTime);
        System.out.printf("%tF\n", dateTime);
        System.out.printf("%tD\n", dateTime);
        System.out.printf("%tT\n", dateTime);
        System.out.printf("%tr\n", dateTime);
    }
}
