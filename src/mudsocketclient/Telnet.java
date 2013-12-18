package mudsocketclient;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;

public final class Telnet implements Observer {

    private static Logger log = Logger.getLogger(Telnet.class.getName());
    LocalConnection local = new LocalConnection();
    RemoteConnection remote = new RemoteConnection("dune.servint.com", 6789);
    List<String> commands = new ArrayList<>();

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
        String cmd = null;
        if (o instanceof RemoteConnection) {
            String line = (String) arg;
            StatelessTriggers.parse(line);
            commands.addAll(StatelessTriggers.getCmd());
        }
        if (o instanceof LocalConnection) {
            commands.add((String) arg);
        }
        StatelessTriggers.clear();
        execute();
    }

    private void execute() {
        try {
            remote.write(commands);
        } catch (IOException ex) {
            log.fine(ex.toString());
        }
        commands = new ArrayList<>();
    }
}