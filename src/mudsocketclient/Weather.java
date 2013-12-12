package mudsocketclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import static java.lang.System.out;


public class Weather {

    public static void main(String[] args) throws UnknownHostException, IOException {
        String host = "rainmaker.wunderground.com";
        int port = 3000;
        int byteOfData;
        {
            try (Socket socket = new Socket(host, port);
                    InputStream inputStream = socket.getInputStream();
                    OutputStream ouputStream = socket.getOutputStream();
                    PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
                    final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
                //also, a thread for reading from stdin
                Thread remoteOutputStream = new Thread() {
                    @Override
                    public void run() {
                        //while loop should go here
                        //   java.net.SocketException: Socket closed
                        //why does this error occur?
                    }
                };
                remoteOutputStream.start();
                while ((byteOfData = inputStream.read()) != -1) {  //put into thread
                    out.print((char) byteOfData);
                }
            }
        }
    }
}