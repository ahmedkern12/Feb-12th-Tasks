import java.util.concurrent.*;

public class Task4 {
    public static void main(String[] args) throws Exception {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate(() -> {
            System.out.println("Cleaning temporary files by " + Thread.currentThread().getName());
        }, 0, 5, TimeUnit.SECONDS);

        scheduler.schedule(() -> {
            scheduler.shutdown();
        }, 20, TimeUnit.SECONDS);
    }
}
