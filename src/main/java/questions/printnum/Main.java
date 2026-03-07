package questions.printnum;

public class Main {

    public static void main(String[] args) throws InterruptedException {
//        Print numbers from 1 to N using 3 threads in sequence (T1 → T2 → T3 repeatedly)

        PrintCounter pc = new PrintCounter(20);
        Thread t1 = new Thread(() -> pc.printCounter(1));
        Thread t2 = new Thread(() -> pc.printCounter(2));
        Thread t3 = new Thread(() -> pc.printCounter(3));

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println("Finished!");

    }


}
