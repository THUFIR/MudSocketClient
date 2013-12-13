package mudsocketclient;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.logging.Logger;

public class Telnet {

    private static Logger log = Logger.getLogger(Telnet.class.getName());

    public Telnet() throws InterruptedException, UnknownHostException, IOException {
        startThreads();
    }

    public static void main(String[] args) throws InterruptedException, UnknownHostException, IOException {
        new Telnet();
    }

    public void startThreads() throws InterruptedException, UnknownHostException, IOException {
        final String host = "rainmaker.wunderground.com";
        final int port = 3000;
        CubbyHole cubbyHole = new CubbyHole();
        Thread producer = new Thread(new Producer(cubbyHole));
        Thread consumer = new Thread(new Consumer(cubbyHole));
        producer.start();
        consumer.start();
    }
}
