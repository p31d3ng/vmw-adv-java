package threads;

public class Simple {
    public static void main(String[] args) {
        Runnable job = new Runnable() {
            int i = 0;

            public void run() {
                System.out.println("Thread " + Thread.currentThread().getName() + " starting");
                for (; i < 10_000; i++) {
                    System.out.println(Thread.currentThread().getName() + " i is " + i);
                }
                System.out.println("Thread " + Thread.currentThread().getName() + " ending");
            }
        };

        Thread t1 = new Thread(job);
        t1.start();

        Thread t2 = new Thread(job);
        t2.start();

        System.out.println("Thread started, main exiting...");
    }
}
