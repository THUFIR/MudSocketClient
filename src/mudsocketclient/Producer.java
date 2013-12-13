package mudsocketclient;

import java.util.Scanner;
import java.util.logging.Logger;

public class Producer implements Runnable {

    private static Logger log = Logger.getLogger(Producer.class.getName());
    private CubbyHole c;

    public Producer(CubbyHole c) {
        this.c = c;
    }

    @Override
    public void run() {
        Scanner scanner;
        String line;
        while (true) {
            scanner = new Scanner(System.in);
            try {
                line = scanner.nextLine();
                log.info(line);
                c.setMessage(line);
            } catch (java.util.NoSuchElementException e) {
                log.fine(e.toString());
            }
        }
    }
}