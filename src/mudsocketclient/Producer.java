package mudsocketclient;

import java.util.Scanner;
import java.util.logging.Logger;

public class Producer implements Runnable {

    private static Logger log = Logger.getLogger(Producer.class.getName());
    private CubbyHole cubbyHole;

    public Producer(CubbyHole cubbyHole) {
        this.cubbyHole = cubbyHole;
    }

    @Override
    public void run() {
        Scanner scanner;
        String line;
        scanner = new Scanner(System.in);
        log.info("ready to scan...");
        line = scanner.nextLine();
        if ((!"".equals(line) && (!"some message".equals(line)))) {
            log.info(line);
            cubbyHole.setMessage(line);
        }
    }
}
