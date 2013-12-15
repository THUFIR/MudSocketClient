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
            confuse();
            fighting();
        } else {
            command = null;
            StatelessTriggers.line = null;
            log.fine(line);
        }
    }

    private static void confuse() {
        if (line.contains("confusing the hell out of")) {
            Pattern pattern = Pattern.compile("[\\w]+(?=\\.)");  //(\w+)\.
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                enemy = matcher.group();
            }
            command = "backstab " + enemy;
        }
    }

    private static void fighting() {
        enemy = null;
        command = null;
        if (line.contains("You are fighting")) {
            log.info(line);
            Pattern pattern = Pattern.compile("(\\w+)$");
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                enemy = matcher.group();
            }
            command = "confuse " + enemy;
            log.info(command);
            checkForNullEnemy();
        }
    }

    private static void checkForNullEnemy() {
        if ((enemy == null) || ("null".equalsIgnoreCase(enemy))) {
            log.info(enemy);
            command = null;
        }
    }

    public static String getCmd() {
        return command;
    }

    public static void clear() {
        command = null;
    }
}