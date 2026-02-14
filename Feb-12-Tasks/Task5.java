import java.util.concurrent.*;

public class Task5 {
    public static void main(String[] args) throws Exception {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);

        ExecutorService executor = Executors.newFixedThreadPool(2);

        Runnable producer = () -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    queue.put("Order-" + i);
                    System.out.println("Produced " + "Order" + i);
                    Thread.sleep(500);
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Runnable consumer = () -> {
            while (true) {
                try {
                    String order = queue.take();
                    System.out.println("Processed " + order + " by " + Thread.currentThread().getName());
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        };

        new Thread(producer).start();
        executor.submit(consumer);

        Thread.sleep(5000);
        executor.shutdownNow();
    }
}
