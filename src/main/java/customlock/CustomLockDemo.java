package customlock;

public class CustomLockDemo {

    // Problem: We have to implement our custom locking mechanism to increment an integer counter.
    // solution: I can make counter thread-safe using synchronized, AtomicInteger, or ReentrantLock.
    // We will not be using here any of these above, interviewer can ask for custom locking mechanism using wait and notify.

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                try {
                    counter.increment();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        Thread t3 = new Thread(task);

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println("Final Counter Value: " + counter.getCount());
    }
}
