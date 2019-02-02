package threads;

import java.util.concurrent.CompletableFuture;

public class CFExample {
    public static void delay(int d) {
        try {
            Thread.sleep(d);
        } catch (InterruptedException ioe) {
        }
    }

    public static CompletableFuture<String> readAFile(String filename) {
        CompletableFuture<String> result = new CompletableFuture<>();
        new Thread(()->{
            delay(2000);
            result.complete("This is data read from a file callled " + filename);
        }).start();
        System.out.println("file read initiated...");
        return result;
    }

    public static void main(String[] args) {
        CompletableFuture<Void> cfv = CompletableFuture.supplyAsync(() -> {
            System.out.println("Starting job");
            delay(1000);
            System.out.println("Job 1 finished");
            return "filename.txt";
        })
                .thenCompose(s -> readAFile(s))
                .thenApply(s -> "Step two " + s.toUpperCase())
                .thenAccept(s -> System.out.println("The job has finished, result is " + s))
                .thenRun(() -> System.out.println("Running the final runnable"));

        System.out.println("Pipeline built");
        cfv.join();

    }
}
