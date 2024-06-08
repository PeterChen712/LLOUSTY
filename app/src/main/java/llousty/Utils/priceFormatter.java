package llousty.Utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

public class priceFormatter {
    public static String formatCurrency(double amount) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');
        String pattern = "#,###.0";
        DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
        return decimalFormat.format(amount);
    }

    public static double formatCurrency(String amountString) {
        try {
            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setGroupingSeparator('.');
            symbols.setDecimalSeparator(',');
            String pattern = "#,###.0";
            DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
            return decimalFormat.parse(amountString).doubleValue();
        } catch (ParseException e) {
            System.err.println("Error: Invalid input string for currency parsing: " + amountString);
            return 0.0;
        }
    }
}