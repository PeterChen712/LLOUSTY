package llousty.Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateGenerator {
    private static final String pattern = "dd/MM/yyyy HH:mm:ss";
    private static final DateTimeFormatter date = DateTimeFormatter.ofPattern(pattern);

    public static String getCurrentDateTimeAsString() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        return currentDateTime.format(date);
    }
}
