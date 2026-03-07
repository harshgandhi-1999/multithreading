package questions.printchar;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        // Print sequence of characters ABC ABC ABC 20 times using 3 threads in sequence (T1 → T2 → T3 repeatedly)
        // thread1 prints A, thread2 prints B and thread3 prints C

        PrintCharacter pc = new PrintCharacter(12);

        Thread t1 = new Thread(() -> pc.printChar(1, 'A'));
        Thread t2 = new Thread(() -> pc.printChar(2, 'B'));
        Thread t3 = new Thread(() -> pc.printChar(3, 'C'));

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println("Finished!");
    }
}
