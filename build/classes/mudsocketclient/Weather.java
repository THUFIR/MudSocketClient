package mudsocketclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Weather {

    public static void main(String[] args) {
        String host = "rainmaker.wunderground.com";
        int port = 3000;
        int c;
        {
            try (Socket socket = new Socket(host, port);
                    PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
                    final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
                while (true) {
                    System.out.println(socket.toString());
                    c = bufferedReader.read();
                    System.out.print((char) c);
                }
            } catch (IOException ex) {
                System.out.println(ex + host + port);
                System.exit(1);
            } finally {
                System.exit(1);

            }
        }
    }
}