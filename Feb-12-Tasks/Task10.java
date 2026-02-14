import java.util.concurrent.*;

public class Task10 {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        long start = System.currentTimeMillis();

        for (int i = 1; i <= 100; i++) {
            int id = i;
            executor.submit(() -> {
                try {
                    Thread.sleep(200);
                    System.out.println("Request " + id + " handled by " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.MINUTES);

        long end = System.currentTimeMillis();
        System.out.println("Total time: " + (end - start) + " ms");
    }
}
