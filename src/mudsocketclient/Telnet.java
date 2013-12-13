package mudsocketclient;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import static java.lang.System.out;

public class Telnet {

    
    public static void main(String[] args) throws UnknownHostException, IOException {
        final String host = "rainmaker.wunderground.com";
        final int port = 3000;

        Thread local = new Thread() {

            @Override
            public void run() {
                Scanner scanner;
                String line;
                while (true) {
                    scanner = new Scanner(System.in);
                    line = scanner.nextLine();
                    out.println("\n\nyou entered\t\"" + line + "\"\n");
                }
            }
        };
        local.start();
        Thread remote = new Thread() {

            @Override
            public void run() {
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
        };

        remote.start();
    }
}
