package mudsocketclient;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;
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
        Deque<String> queue = new ConcurrentLinkedDeque<>();
        queue.add("first");
        Thread producer = new Thread(new Producer(queue));
        Thread consumer = new Thread(new Consumer(queue));
        producer.start();
        consumer.start();
    }
}
