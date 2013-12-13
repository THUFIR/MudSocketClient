package mudsocketclient;

import java.util.Observable;
import java.util.Scanner;
import static java.lang.System.out;

public class LocalIO extends Observable implements Runnable {

    final String host = "rainmaker.wunderground.com";
    final int port = 3000;

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
}