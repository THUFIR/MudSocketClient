package mudsocketclient;

import java.util.concurrent.Semaphore;

public class Telnet {

    public Telnet() throws InterruptedException {
        startThreads();
    }

    public static void main(String[] args) throws InterruptedException {
        new Telnet();
    }

    public void startThreads() throws InterruptedException {
        Semaphore s = new Semaphore(1, true);
        
        Thread localThread = new Thread(new LocalIO());
        Thread remoteThread = new Thread(new RemoteIO());

        localThread.start();
        remoteThread.start();
    }
}
