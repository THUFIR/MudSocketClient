package reports;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMapBuilder {

    public static Map<String, Ratio> stringToRatiosMap(String input) {
        Map<String, Ratio> mapOfStringsToRatios = new HashMap<>();
        Map<String, String> strings = stringMap(input);
        Pattern fraction = Pattern.compile("(\\d+)/(\\d+)");
        Pattern wholeNumber = Pattern.compile("(\\d+)");
        Pattern percent = Pattern.compile("(\\d+)%");
        Matcher matcher;
        int numerator, denominator;
        Ratio ratio = null;             //need numerator and denominator values
        for (Entry<String, String> e : strings.entrySet()) {
            matcher = wholeNumber.matcher(e.getValue());
            while (matcher.find()) {
                numerator = Integer.parseInt(matcher.group(1));
                denominator = 1;
                ratio = new Ratio(numerator, denominator);
            }
            matcher = fraction.matcher(e.getValue());
            while (matcher.find()) {
                numerator = Integer.parseInt(matcher.group(1));
                denominator = Integer.parseInt(matcher.group(2));
                ratio = new Ratio(numerator, denominator);
            }
            matcher = percent.matcher(e.getValue());
            while (matcher.find()) {
                numerator = Integer.parseInt(matcher.group(1));
                denominator = 100;
                ratio = new Ratio(numerator, denominator);
            }
            mapOfStringsToRatios.put(e.getKey(), ratio);
        }
        return mapOfStringsToRatios;
    }

    private static Map<String, String> stringMap(String input) {
        Map<String, String> strings = new HashMap<>();
        Pattern pattern = Pattern.compile("(\\w+): +(\\S+)");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            strings.put(matcher.group(1), matcher.group(2));
        }
        return strings;
    }
}