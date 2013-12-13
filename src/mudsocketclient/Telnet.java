package mudsocketclient;

import java.util.Observable;
import java.util.Observer;

public class Telnet implements Observer {

    public Telnet() {
        startThreads();
    }

    public static void main(String[] args) {
        new Telnet();
    }

    public void startThreads() {
        LocalIO local = new LocalIO();
        Thread localThread = new Thread(new LocalIO());
        Thread remoteThread = new Thread(new RemoteIO());

        //how do observe, or know what's happening with
        //the local thread?

        local.addObserver(this); //is this ok???

        //really, I don't want to observe the Thread, but the anonymous
        //LocalIO reference...


        localThread.start();
        remoteThread.start();
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
