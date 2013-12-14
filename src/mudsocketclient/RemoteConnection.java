package mudsocketclient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Logger;

public class RemoteConnection {

    private static Logger log = Logger.getLogger(RemoteConnection.class.getName());
    final String host = "rainmaker.wunderground.com";
    final int port = 3000;
    private final Socket socket;
    private final InputStream inputStream;
    private final OutputStream outputStream;

    public RemoteConnection() throws UnknownHostException, IOException {
        socket = new Socket(host, port);
        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();
    }

    public void write(String line) throws IOException {
        log.info(line);
        byte[] actionBytes = line.getBytes();
        outputStream.write(actionBytes);
        outputStream.write(13);
        outputStream.write(10);
        outputStream.flush();
        log.info("socket connected?\t\t" + socket.isConnected());
    }

    void read() {
        Thread readRemote = new Thread() {

            @Override
            public void run() {
                while (true) {
                    try {
                        System.out.print((char) inputStream.read());
                    } catch (IOException ioe) {
                        log.fine(ioe.toString());
                    }
                }
            }
        };
        readRemote.start();
    }
}