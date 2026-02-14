import java.util.concurrent.*;

class Account {
    private int balance = 10000;

    public synchronized void withdraw(String user, int amount) {
        if (balance >= amount) {
            System.out.println(user + " withdrawing " + amount);
            balance -= amount;
            System.out.println(user + " completed withdrawal. Balance: " + balance);
        } else {
            System.out.println(user + " insufficient balance");
        }
    }
}

public class Task8 {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        Account account = new Account();

        for (int i = 1; i <= 5; i++) {
            int user = i;
            executor.submit(() -> {
                account.withdraw("User-" + user, 1000);
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
    }
}
