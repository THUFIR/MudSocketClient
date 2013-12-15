package mudsocketclient;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StatelessTriggers {

    private static Logger log = Logger.getLogger(StatelessTriggers.class.getName());
    private String cmd = null;
    private String line = null;

    public void parse(String line) {
        this.line = line;
        if (!line.equalsIgnoreCase(line)) {
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
            String enemy = null;
            while (matcher.find()) {
                enemy = matcher.group();
            }
            cmd = "backstab " + enemy;
        }
    }

    public String getCmd() {
        return cmd;
    }

    public void clear() {
        cmd = null;
    }

    private void fighting() {
        if (line.contains("you are fighting")) {
            Pattern pattern = Pattern.compile("[\\w]+(?=\\.)");  //(\w+)\.
            Matcher matcher = pattern.matcher(line);
            String enemy = null;
            while (matcher.find()) {
                enemy = matcher.group();
            }
            cmd = "confuse" + enemy;
        }
    }
}