package mudsocketclient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Logger;

public class Consumer implements Runnable {

    private static Logger log = Logger.getLogger(Consumer.class.getName());
    final String host = "rainmaker.wunderground.com";
    final int port = 3000;
    private CubbyHole cubbyHole;
    private final Socket socket;
    private final InputStream inputStream;
    private final OutputStream outputStream;

    public Consumer(CubbyHole cubbyHole) throws UnknownHostException, IOException {
        this.cubbyHole = cubbyHole;
        socket = new Socket(host, port);
        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();
    }

    @Override
    public void run() {
        log.info(inputStream.toString());
        log.info(outputStream.toString());
        log.info(cubbyHole.toString());
        while (!Thread.currentThread().isInterrupted()) {
            try {
                System.out.print((char) inputStream.read());
                if (!"some message".equals(cubbyHole.getMessage())) {
                    log.info(cubbyHole.toString());
                }
            } catch (IOException ex) {
                log.fine(ex.toString());
            }
        }
    }
}
