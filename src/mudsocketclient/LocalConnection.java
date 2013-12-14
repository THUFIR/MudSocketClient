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
                while (true) {
                    Scanner scanner;
                    scanner = new Scanner(System.in);
                    String line = scanner.nextLine();
                    setChanged();
                    notifyObservers(line);
                }
            }
        };
        readFromConsole.start();
    }
}