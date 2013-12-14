package mudsocketclient;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;

public final class Telnet implements Observer {

    private static Logger log = Logger.getLogger(Telnet.class.getName());
    LocalConnection local = new LocalConnection();
    RemoteConnection remote = new RemoteConnection();
    StatelessTriggers triggers = new StatelessTriggers();
    private Deque<String> cmds = new ArrayDeque<>();

    public Telnet() throws InterruptedException, UnknownHostException, IOException {
        startThreads();
    }

    public static void main(String[] args) throws InterruptedException, UnknownHostException, IOException {
        new Telnet();
    }

    public void startThreads() throws InterruptedException, UnknownHostException, IOException {
        local.addObserver(this);
        remote.addObserver(this);
        local.read();
        remote.read();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof RemoteConnection) {
            String line = (String) arg;
            triggers.parse(line);
            String cmd = triggers.getCmd();
            cmds.add(cmd);
        }
        if (o instanceof LocalConnection) {
            cmds.add((String) arg);
        }
        execute();
    }

    private void execute() {
        while (cmds.size() > 0) {
            try {
                remote.write(cmds.pop());
            } catch (IOException ioe) {
                log.warning(ioe.toString());
            }
        }
    }
}