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
        int c;
        InputOutput io = new InputOutput();
        int intVal;
        char ch;
        StringBuilder sb = null;
        {
            try (Socket socket = new Socket(host, port);
                    InputStream inputStream = socket.getInputStream();
                    OutputStream ouputStream = socket.getOutputStream();
                    PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
                    final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
                while ((intVal = inputStream.read()) != -1) {
                    ch = (char) intVal;
                    out.print(ch);
                   // sb.append(ch);
                    if (intVal == 13) {
                        sb = new StringBuilder();
                    }
                }
            }
        }
    }
}