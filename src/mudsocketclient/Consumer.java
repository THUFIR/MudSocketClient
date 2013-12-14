package mudsocketclient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Deque;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

public class Consumer implements Runnable {

    private static Logger log = Logger.getLogger(Consumer.class.getName());
    final String host = "rainmaker.wunderground.com";
    final int port = 3000;
    private final Socket socket;
    private final InputStream inputStream;
    private final OutputStream outputStream;
    private final Deque<String> queue;
    private String stringFromProducer = "line scan of user input";

    public Consumer(Deque<String> queue) throws UnknownHostException, IOException {
        this.queue = queue;
        socket = new Socket(host, port);
        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.print((char) inputStream.read());
            } catch (IOException ex) {
                log.fine(ex.toString());
            }
            try {
                stringFromProducer = queue.pop();
                log.info(stringFromProducer);
            } catch (NoSuchElementException ex) {
                log.fine(ex.toString());
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    log.fine(e.toString());
                }
            }
        }
    }
}
