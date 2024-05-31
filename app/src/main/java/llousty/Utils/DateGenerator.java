package llousty.Utils;

import java.time.LocalDateTime;
import java.time.Duration;
import java.time.format.DateTimeFormatter;

public class DateGenerator {
    private static final String pattern = "dd/MM/yyyy HH:mm:ss";
    private static final String shortDatePattern = "dd/MM/yy";
    
    public static String getCurrentDateTimeAsString() {
        DateTimeFormatter date = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime currentDateTime = LocalDateTime.now();
        return currentDateTime.format(date);
    }

    public static String getCurrentDateAsShortString() {
    DateTimeFormatter shortDateFormatter = DateTimeFormatter.ofPattern(shortDatePattern);
    LocalDateTime currentDateTime = LocalDateTime.now();
    return currentDateTime.format(shortDateFormatter);
    }


    public static String getRelativeTimeString(LocalDateTime dateTime) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        Duration duration = Duration.between(dateTime, currentDateTime);

        long days = duration.toDays();
        if (days >= 7) {
            return days / 7 + " weeks ago";
        } else if (days > 0) {
            return days + " day" + (days > 1 ? "s" : "") + " ago";
        } else {
            long hours = duration.toHours();
            if (hours > 0) {
                return hours + " hour" + (hours > 1 ? "s" : "") + " ago";
            } else {
                long minutes = duration.toMinutes();
                if (minutes > 0) {
                    return minutes + " minute" + (minutes > 1 ? "s" : "") + " ago";
                } else {
                    return "just now";
                }
            }
        }
    }
}
