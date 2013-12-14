package mudsocketclient;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StatelessTriggers {

    private static Logger log = Logger.getLogger(StatelessTriggers.class.getName());
    private String cmd = null;

    public void parse(String line) {

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
}