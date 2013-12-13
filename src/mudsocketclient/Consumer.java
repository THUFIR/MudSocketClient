package mudsocketclient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Logger;

public class Consumer implements Runnable {

    private static Logger log = Logger.getLogger(Consumer.class.getName());
    final String host = "rainmaker.wunderground.com";
    final int port = 3000;
    private CubbyHole cubbyHole;
    private final InputStream inputStream;
    private final OutputStream outputStream;

    public Consumer(CubbyHole cubbyHole, InputStream inputStream, OutputStream outputStream) {
        this.cubbyHole = cubbyHole;
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    @Override
    public void run() {
        log.info(inputStream.toString());
        log.info(outputStream.toString());
        log.info(cubbyHole.toString());
        try {
            System.out.print((char) inputStream.read());
        } catch (IOException ex) {
            log.fine(ex.toString());
        }
    }
}
