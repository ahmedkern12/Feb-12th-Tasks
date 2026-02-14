import java.util.concurrent.locks.ReentrantLock;

class Resource {
    private final ReentrantLock lock = new ReentrantLock();
    private final String name;

    Resource(String name) {
        this.name = name;
    }

    ReentrantLock getLock() {
        return lock;
    }

    String getName() {
        return name;
    }
}

public class Task7 {
    static void process(Resource r1, Resource r2) {
        Resource first = r1;
        Resource second = r2;

        if (System.identityHashCode(first) > System.identityHashCode(second)) {
            first = r2;
            second = r1;
        }

        first.getLock().lock();
        try {
            second.getLock().lock();
            try {
                System.out.println(Thread.currentThread().getName() + " processing " + first.getName() + " & " + second.getName());
            } finally {
                second.getLock().unlock();
            }
        } finally {
            first.getLock().unlock();
        }
    }

    public static void main(String[] args) {
        Resource payment = new Resource("Payment");
        Resource inventory = new Resource("Inventory");

        new Thread(() -> process(payment, inventory)).start();
        new Thread(() -> process(inventory, payment)).start();
    }
}
