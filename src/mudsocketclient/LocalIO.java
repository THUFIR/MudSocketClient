package mudsocketclient;

import java.util.Scanner;
import static java.lang.System.out;

public class LocalIO implements Runnable {

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