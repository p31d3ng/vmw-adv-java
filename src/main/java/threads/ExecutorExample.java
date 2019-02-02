package threads;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

class MyJob implements Callable<String> {
    private static int nextJobId = 0;
    private int jobId = nextJobId++;

    @Override
    public String call() throws Exception {
        System.out.println("Job " + jobId + " starting...");
        Thread.sleep((int)(Math.random() * 2000) + 1000);
        if (Math.random() > 0.7) throw new SQLException("Bad data!!!");
        return "Database complete for job id " + jobId;
    }
}

public class ExecutorExample {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(2);
        List<Future<String>> handles = new ArrayList<>();
        final int JOB_COUNT = 8;
        for (int i = 0; i < JOB_COUNT; i++) {
            handles.add(es.submit(new MyJob()));
        }

        for (Future<String> fs : handles) {
            try {
                String result = fs.get();
                System.out.println("Job returned " + result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                System.out.println("Execution threw a: " + e.getCause().getClass() + " message is " + e.getCause().getMessage());;
            }
        }

        es.shutdown();
    }
}
