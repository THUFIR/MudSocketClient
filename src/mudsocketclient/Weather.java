package mudsocketclient;

import static java.lang.System.out;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Weather {

    public static void main(String[] args) throws UnknownHostException, IOException {
        String host = "rainmaker.wunderground.com";
        int port = 3000;
        int byteOfData;

        char charFromByteOfData;
        {
            try (Socket socket = new Socket(host, port);
                    InputStream inputStream = socket.getInputStream();
                    OutputStream ouputStream = socket.getOutputStream();
                    PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
                    final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
                Thread readInput = new Thread() {

                    @Override
                    public void run() {
                        out.println("hi");
                    }
                };
                while ((byteOfData = inputStream.read()) != -1) {
                    charFromByteOfData = (char) byteOfData;
                    out.print(charFromByteOfData);
                }
            }

        }
    }
}