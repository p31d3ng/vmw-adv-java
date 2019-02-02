package threads;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProdCons {
    public static void main(String[] args) {
        BlockingQueue<int[]> q = new ArrayBlockingQueue<>(10);
        new Thread(()-> {
            System.out.println("Starting producer");
            for (int i = 0; i < 1000; i++) {
                try {
                    int [] data = {i, 0};
                    // transactionally incorrect!!!
                    data[1] = i;
                    // phew, ok again.
                    if (i == 998) {
                        data[1] = 997; // test!
                    }
                    q.put(data);
                    if (i < 500) {
                        Thread.sleep(10);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Ending producer");
        }).start();
        new Thread(()-> {
            System.out.println("Starting consumer");
            for (int i = 0; i < 1000; i++) {
                try {
                    if (i > 500) {
                        Thread.sleep(10);
                    }
                    int [] v = q.take();
                    if (v[0] != v[1] || v[0] != i) {
                        System.out.println("**** ERROR, expected " + i + " got [" + v[0] + ", " + v[1] + "]");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Ending consumer");
        }).start();

        System.out.println("Main exiting...");
    }
}
