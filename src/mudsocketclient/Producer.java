package mudsocketclient;

import java.util.Deque;
import java.util.Scanner;
import java.util.logging.Logger;

public class Producer implements Runnable {

    private static Logger log = Logger.getLogger(Producer.class.getName());
    private final Deque<String> queue;

    public Producer(Deque<String> queue) {
        this.queue = queue;
    }

    
    @Override
    public void run() {
        while (true) {
            Scanner scanner;
            String line;
            scanner = new Scanner(System.in);
            line = scanner.nextLine();
            queue.add(line);
            log.info(line);   //echoes that line was entered
        }
    }
}
