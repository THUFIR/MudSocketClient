package mudsocketclient;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StatelessTriggers {

    private static Logger log = Logger.getLogger(StatelessTriggers.class.getName());
    private String cmd = null;
    private String line = null;
    private String enemy = null;

    public void parse(String line) {
        this.line = line;
        if (!"null".equalsIgnoreCase(line)) {
            confuse();
            fighting();
        } else {
            cmd = null;
            this.line = null;
            log.fine(line);
        }
    }

    private void confuse() {
        if (line.contains("confusing the hell out of")) {
            Pattern pattern = Pattern.compile("[\\w]+(?=\\.)");  //(\w+)\.
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                enemy = matcher.group();
            }
            cmd = "backstab " + enemy;
        }
    }

    private void fighting() {//You are fighting Citizen
        if (line.contains("You are fighting")) {
            log.info(line);
            Pattern pattern = Pattern.compile("(\\w+)$");  //(\w+)\.
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                enemy = matcher.group();
            }
            cmd = "confuse " + enemy;
            nullEnemy();
        }
    }

    private void nullEnemy() {
        if ((enemy == null) || ("null".equalsIgnoreCase(enemy))) {
            cmd = null;
        }
    }

    public String getCmd() {
        return cmd;
    }

    public void clear() {
        cmd = null;
    }
}