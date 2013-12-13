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
        log.info("socket is\t" + socket.isConnected());
        log.info(cubbyHole.toString());
        try {
            while (true) {

                log.fine("consumer is running\t\t\t" + cubbyHole);
                System.out.print((char) inputStream.read());
                if ((!"".equals(cubbyHole.toString()) && (!"some message".equals(cubbyHole.toString())))) {
                    log.info(cubbyHole.toString());
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();

        }
    }
}
