package mudsocketclient;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StatelessTriggers {

    private static Logger log = Logger.getLogger(StatelessTriggers.class.getName());
    private static String line = null;
    private static List<String> c = new ArrayList<>();

    public static void parse(String line) {
        StatelessTriggers.line = line;
        if ((!"null".equalsIgnoreCase(line)) && (line != null)) {
            confused();
            fighting();
            killed();
        } else {
            StatelessTriggers.line = null;
        }
    }

    public static List<String> getCmd() {
        return c;
    }

    public static void clear() {
        c = new ArrayList<>();
    }

    private static String both(boolean dot) {
        Pattern pattern = (dot) ? Pattern.compile("[\\w]+(?=\\.)") : Pattern.compile("(\\w+)$");
        Matcher matcher = pattern.matcher(line);
        String enemy = null;
        while (matcher.find()) {
            enemy = matcher.group();
        }
        return enemy;
    }

    private static void confused() {
        if (line.contains("confusing the hell out of")) {
            c.add("backstab " + both(true));
        }
    }

    private static void killed() {
        if (line.contains("You killed")) {
            c.add("draw from " + both(true));
            c.add("transfuse");
            c.add("process corpse");
            c.add("get all");
            c.add("monitor");
            c.add("glance");
            c.add("monitor");
        }
    }

    private static void fighting() {
        if (line.contains("You are fighting")) {
            c.add("confuse " + both(false));
            c.add("heartplunge");
            c.add("enervate");
        }
    }
}