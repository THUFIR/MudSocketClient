package reports;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.lang.System.out;

public class Ratios {

    public static Map<String, Ratio> stringToRatiosMap(String input) {
        Map<String, Ratio> stringsToRatios = new HashMap<>();
        Map<String, String> strings = stringMap(input);
        Pattern fraction = Pattern.compile("(\\d+)/(\\d+)");
        Pattern whole = Pattern.compile("(\\d+)");
        Pattern percent = Pattern.compile("(\\d+)%");
        Matcher matcher;
        int num, den;
        Ratio ratio = null;
        for (Map.Entry<String, String> e : strings.entrySet()) {

            matcher = whole.matcher(e.getValue());
            while (matcher.find()) {
                num = Integer.parseInt(matcher.group(1));
                den = 1;
                ratio = new Ratio(num, den);
            }

            matcher = fraction.matcher(e.getValue());
            while (matcher.find()) {
                num = Integer.parseInt(matcher.group(1));
                den = Integer.parseInt(matcher.group(2));
                ratio = new Ratio(num, den);
            }


            matcher = percent.matcher(e.getValue());
            while (matcher.find()) {
                num = Integer.parseInt(matcher.group(1));
                den = 100;
                ratio = new Ratio(num, den);
            }

            stringsToRatios.put(e.getKey(), ratio);
        }
        return stringsToRatios;
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

    public static void printMap(Map<?, ?> map) {
        for (Map.Entry<?, ?> e : map.entrySet()) {
            String key = e.getKey().toString();
            String val = e.getValue().toString();
            out.println(key + "\t\t" + val);
        }
    }
}