package android.unitedremote.com.unitedremotemobilechallenge.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class StarsConverter {

    /**
     * Convert an integer into a thousand representation.
     * @param stars number
     * @return string representation
     */
    public static String convert(int stars) {

        if(stars < 1000)
            return String.valueOf(stars);

        double result = stars/1000.0;
        int remainder = stars%1000;
        NumberFormat formatter;
        if(remainder < 100)
            formatter = new DecimalFormat("#0");
        else
            formatter = new DecimalFormat("#0.0");

        return String.valueOf(formatter.format(result)).concat("k");
    }
}
