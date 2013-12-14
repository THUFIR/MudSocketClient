package mudsocketclient;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.logging.Logger;


/*
 * sorta kinda works.  The Consumer connects to the remote telnet client
 * correctly and prints the output.  Also, when the NoSuchElementException
 * log level is set to info or higher it will spam the console with output.
 * this indicates that the Consumer infinite while loop is excecuting.
 * 
 * The Producer works fine as an infinite loop, it correctly echoes back user
 * input to the console.
 * 
 * However, probably the program gets "stuck" in the Producer loop.
 * 
 * It's hard to debug because the options are either not enough info, or way
 * too much spamming from producer.
 */


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
