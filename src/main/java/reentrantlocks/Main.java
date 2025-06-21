package reentrantlocks;

public class Main {

    // Intrinsic locks: These are built into every object in java. when we use a synchronized keyword, these locks are used automatically.
    // Extrinsic locks: These are advanced locks that we can control using the Lock class in concurrent package
    // so we have more control over locking.
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount();
        Runnable task = () -> bankAccount.withdraw(50);

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");

        t1.start();
        t2.start();

    }
}
