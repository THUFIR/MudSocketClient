package mudsocketclient;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Logger;

public class Telnet {

    private static Logger log = Logger.getLogger(Telnet.class.getName());

    public Telnet() throws InterruptedException {
        startThreads();
    }

    public static void main(String[] args) throws InterruptedException {
        new Telnet();
    }

    public void startThreads() throws InterruptedException {
        final String host = "rainmaker.wunderground.com";
        final int port = 3000;
        CubbyHole cubbyHole = new CubbyHole();
        try {
            Socket socket = new Socket(host, port);
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            Thread producer = new Thread(new Producer(cubbyHole));
            Thread consumer = new Thread(new Consumer(cubbyHole, inputStream, outputStream));
            producer.start();
            consumer.start();
        } catch (Exception e) {
            log.fine(e.toString());
        }
    }
}
