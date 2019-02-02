package threads;

public class TryVolatile {
    private volatile static int count = 0;
    public static void main(String[] args) throws Throwable {
        Runnable r = () -> {
            for (int i = 0; i < 10_000; i++) {
                count++;
            }
        };

        Thread t1 = new Thread(r);
        t1.start();
        Thread t2 = new Thread(r);
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Count is " + count);
    }
}
