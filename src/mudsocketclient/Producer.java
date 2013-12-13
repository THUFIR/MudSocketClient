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
        scanner = new Scanner(System.in);
        log.info("ready to scan...");
        while(Thread.currentThread().isInterrupted()){
            try {
                line = scanner.nextLine();
                log.info(line);
                c.setMessage(line);
            } catch (java.util.NoSuchElementException e) {
                log.info(e.toString());
            }
        }
    }
}
