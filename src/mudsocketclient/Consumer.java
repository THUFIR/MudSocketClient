package mudsocketclient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Deque;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumer implements Runnable {

    private static Logger log = Logger.getLogger(Consumer.class.getName());
    final String host = "rainmaker.wunderground.com";
    final int port = 3000;
    private CubbyHole cubbyHole = new CubbyHole();
    private final Socket socket;
    private final InputStream inputStream;
    private final OutputStream outputStream;
    private final Deque<CubbyHole> queue;

    public Consumer(Deque<CubbyHole> queue) throws UnknownHostException, IOException {
        this.queue = queue;
        socket = new Socket(host, port);
        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();
    }

    @Override
    public void run() {
<<<<<<< HEAD

        
=======
>>>>>>> 28330a60c0f54d64b288410bd0b32973b7bf2f25
        while (true) {
            try {
                System.out.print((char) inputStream.read());
                if ((!"".equals(cubbyHole.toString()) && (!"some message".equals(cubbyHole.toString())))) {
                    log.fine(cubbyHole.toString());
                }
            } catch (IOException ex) {
                log.fine(ex.toString());
            }

            try {
                cubbyHole = queue.pop();
                log.log(Level.FINE, "consumer is running\t\t\t{0}", cubbyHole);
            } catch ( NoSuchElementException ex) {
                log.fine(ex.toString());
            }
        }
    }
}
