package mudsocketclient;

import java.util.Observable;
import java.util.Scanner;
import java.util.logging.Logger;

public class LocalConnection extends Observable {

    private static Logger log = Logger.getLogger(LocalConnection.class.getName());

    public LocalConnection() {
    }

    public void read() {
        Thread readFromConsole = new Thread() {

            @Override
            public void run() {
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    String line = scanner.nextLine();
                    setChanged();
                    notifyObservers(line);
                }
            }
        };
        readFromConsole.start();
    }
}