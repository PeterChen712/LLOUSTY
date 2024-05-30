package llousty.Utils;

public class RateMaker {

    public static String calculateRatingAverage(int totalRates, double valueRate) {
        if (totalRates > 0) {
            double average = valueRate / totalRates;
            return "Rate: " + String.format("%.1f/5", average);
        }
        else{
            return "Rate: -";
        }
    }
}