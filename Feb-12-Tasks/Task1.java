import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Task1 {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i = 1; i <= 20; i++) {
            int requestId = i;
            executor.submit(() -> {
                System.out.println("Request " + requestId + " handled by " + Thread.currentThread().getName());
            });
        }

        executor.shutdown();
    }
}
