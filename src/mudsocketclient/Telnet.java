package mudsocketclient;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;

public final class Telnet implements Observer {

    private static Logger log = Logger.getLogger(Telnet.class.getName());
    LocalConnection local = new LocalConnection();
    RemoteConnection remote = new RemoteConnection();

    public Telnet() throws InterruptedException, UnknownHostException, IOException {
        startThreads();
    }

    public static void main(String[] args) throws InterruptedException, UnknownHostException, IOException {
        new Telnet();
    }

    public void startThreads() throws InterruptedException, UnknownHostException, IOException {
        local.addObserver(this);
        local.read();
        remote.read();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof LocalConnection) {
            String line = (String) arg;
            try {
                remote.write(line);
            } catch (IOException ioe) {
                log.warning(ioe.toString());
            }
        }
    }
}
