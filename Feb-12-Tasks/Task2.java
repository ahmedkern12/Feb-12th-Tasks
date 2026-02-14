import java.util.concurrent.*;
import java.util.*;

class InterestTask implements Callable<Double> {
    private double principal;
    private double rate;

    InterestTask(double principal, double rate) {
        this.principal = principal;
        this.rate = rate;
    }

    public Double call() {
        return principal * rate / 100;
    }
}

public class Task2 {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(4);

        List<Callable<Double>> tasks = Arrays.asList(
                new InterestTask(10000, 5),
                new InterestTask(20000, 6),
                new InterestTask(15000, 4),
                new InterestTask(30000, 7)
        );

        List<Future<Double>> futures = new ArrayList<>();

        for (Callable<Double> task : tasks) {
            futures.add(executor.submit(task));
        }

        for (Future<Double> future : futures) {
            System.out.println("Interest: " + future.get());
        }

        executor.shutdown();
    }
}
