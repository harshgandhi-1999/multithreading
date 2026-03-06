package questions.easy.evenodd;

public class Main {
    public static void main(String[] args) throws InterruptedException{

        // Question: Print even and odd using two different threads but shared counter variable

        // Here same shared object pc and shared counter is used for both threads
        // so lock will be acquired on pc object
        PrintCounter pc = new PrintCounter();
        Thread t1 = new Thread(pc::printEven);
        Thread t2 = new Thread(pc::printOdd);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Finished");

    }
}
