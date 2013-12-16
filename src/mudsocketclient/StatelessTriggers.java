package mudsocketclient;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StatelessTriggers {

    private static Logger log = Logger.getLogger(StatelessTriggers.class.getName());
    private static String command = null;
    private static String line = null;
    private static String enemy = null;

    public static void parse(String line) {
        StatelessTriggers.line = line;
        if ((!"null".equalsIgnoreCase(line)) && (line != null)) {
            confused();
            fighting();
            killed();
            needle();
        } else {
            command = null;
            StatelessTriggers.line = null;
        }
    }

    public static String getCmd() {
        return command;
    }

    public static void clear() {
        enemy = null;
        command = null;
    }

    private static void dot() {
        Pattern pattern = Pattern.compile("[\\w]+(?=\\.)");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            enemy = matcher.group();
        }
    }

    private static void noDot() {
        Pattern pattern = Pattern.compile("(\\w+)$");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            enemy = matcher.group();
        }
    }

    private static void confused() {
        if (line.contains("confusing the hell out of")) {
            dot();
            command = "backstab " + enemy;
        }
    }

    private static void killed() {
        if (line.contains("You killed")) {
            dot();
            command = "draw from " + enemy;
        }
    }

    private static void fighting() {
        if (line.contains("You are fighting")) {
            noDot();
            command = "confuse " + enemy;
        }
    }

    private static void needle() {
        if (line.contains("You put a needle in the corpse")) {
            dot();
            command = "transfuse from "+ enemy;
        }
    }
        
        
    private static void transfusion() {
        if (line.contains("You pull your transfusion tubes out")) {
            command = "process corpse";
        }
    }
}