package threads;

public class TimingOne {
    private static boolean stop = false;

    public static void main(String[] args) throws Throwable {
        new Thread(() -> {
            System.out.println("Worker starting, waiting for something");
            while (! stop)
                ;
            System.out.println("Worker stopping...");
        }).start();

        Thread.sleep(2000);
        stop = true;
        System.out.println("Notified worker, main exiting...");
    }
}
