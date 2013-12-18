package mudsocketclient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
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
            monitor();
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

    private static void monitor() {
        if (line.contains("ADRENALINE")) {
            Pattern pattern = Pattern.compile("(\\w+): +(\\S+)");
            Matcher matcher = pattern.matcher(line);
            int hpMin, hpMax, cpMin, cpMax, adr, end, berserk, enemyPerc;
            Map<String, String> stats = new HashMap<>();
            while (matcher.find()) {
                stats.put(matcher.group(1), matcher.group(2));
            }
            for (Map.Entry<String, String> e : stats.entrySet()) {
                String key = e.getKey();
                String val = e.getValue();
                log.info(key + "\t" + val);
            }
        }
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