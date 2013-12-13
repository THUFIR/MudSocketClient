package mudsocketclient;

public class Telnet {

    public Telnet() throws InterruptedException {
        startThreads();
    }

    public static void main(String[] args) throws InterruptedException {
        new Telnet();
    }

    public void startThreads() throws InterruptedException {       

        CubbyHole cubbyHole = new CubbyHole();
        
        Thread producer = new Thread(new Producer(cubbyHole));
        Thread consumer = new Thread(new Consumer(cubbyHole));

        producer.start();
        consumer.start();
    }
}
