package mudsocketclient;

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
        int c;
        InputOutput io = new InputOutput();
        {
            try (Socket socket = new Socket(host, port);
                    OutputStream out = socket.getOutputStream();
                    InputStream in = socket.getInputStream();
                    PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
                    final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
                io.readWriteParse(in, out);
            }
        }
    }
}