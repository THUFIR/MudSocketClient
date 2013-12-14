package mudsocketclient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.logging.Logger;

public class RemoteConnection extends Observable {

    private static Logger log = Logger.getLogger(RemoteConnection.class.getName());
    final String host = "dune.servint.com";
    final int port = 6789;
    private final Socket socket;
    private final InputStream inputStream;
    private final OutputStream outputStream;

    public RemoteConnection() throws UnknownHostException, IOException {
        socket = new Socket(host, port);
        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();
    }

    public void write(String line) throws IOException {
        try {
            byte[] bytes = line.getBytes();
            outputStream.write(bytes);
            outputStream.write(13);
            outputStream.write(10);
            outputStream.flush();
        } catch (NullPointerException npe) {
            log.fine(npe.toString());
        }
    }

    void read() {
        Thread readRemote = new Thread() {

            @Override
            public void run() {
                StringBuilder sb = new StringBuilder();
                char ch;
                int i;
                while (true) {
                    try {
                        i = inputStream.read();
                        ch = (char) i;
                        sb.append(ch);
                        System.out.print(ch);
                        if (i == 13) {
                            setChanged();
                            notifyObservers(sb.toString());
                            log.fine(sb.toString());
                            sb = new StringBuilder();
                        }
                    } catch (IOException ioe) {
                        log.fine(ioe.toString());
                    }
                }
            }
        };
        readRemote.start();
    }
}