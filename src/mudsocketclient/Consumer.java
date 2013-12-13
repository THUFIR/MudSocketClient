package mudsocketclient;

import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import static java.lang.System.out;

public class Consumer implements Runnable {

    private static Logger log = Logger.getLogger(Consumer.class.getName());
    final String host = "rainmaker.wunderground.com";
    final int port = 3000;
    private CubbyHole c;

    public Consumer(CubbyHole c) {
        this.c = c;
    }

    @Override
    public void run() {
        log.info(c.getMessage());
        c.setMessage(new String());
        int byteOfData;
        try (Socket socket = new Socket(host, port);
                InputStream inputStream = socket.getInputStream();
                OutputStream ouputStream = socket.getOutputStream();
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
                final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            while ((byteOfData = inputStream.read()) != -1) {
                out.print((char) byteOfData);
            }
        } catch (Exception e) {
            out.println(e);
        }
    }
}
