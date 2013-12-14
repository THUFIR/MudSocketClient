package mudsocketclient;

import java.util.Deque;
import java.util.Scanner;
import java.util.logging.Logger;

public class Producer implements Runnable {

    private static Logger log = Logger.getLogger(Producer.class.getName());
    private CubbyHole cubbyHole = new CubbyHole();
    private final Deque<CubbyHole> queue;

    public Producer(Deque<CubbyHole> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Scanner scanner;
        String line;
        scanner = new Scanner(System.in);
        line = scanner.nextLine();
        cubbyHole.setMessage(line);
        queue.add(cubbyHole);
    }
}
