package questions.easy;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Question1 {

//    private static final Object lockObject = new Object();
    private static int counter = 0;
    private static final int MAX_NUM = 20;

    private static final Lock reentrantLock = new ReentrantLock();
    private static final Condition evenCondition = reentrantLock.newCondition();
    private static final Condition oddCondition = reentrantLock.newCondition();

    public static void main(String[] args) {

        // EVEN ODD PRINTER

        // Used Reentrant lock, await, signal to solve this
        // We can also use wait, notify in synchronized block to solve this.


        Thread evenPrinter = new Thread(Question1::printEven);
        Thread oddPrinter = new Thread(Question1::printOdd);

        evenPrinter.start();
        oddPrinter.start();
    }

    private static void printEven() {
        while (counter <= MAX_NUM) {
            reentrantLock.lock();
            try {
                while (counter % 2 != 0) {
                    evenCondition.await();
                }
                if (counter <= MAX_NUM) {
                    System.out.println("Even: " + counter);
                    counter++;
                }
                oddCondition.signal(); // Wake up odd thread
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    private static void printOdd() {
        while (counter <= MAX_NUM) {
            reentrantLock.lock();
            try {
                while (counter % 2 == 0) {
                    oddCondition.await();
                }
                if (counter <= MAX_NUM) {
                    System.out.println("Odd: " + counter);
                    counter++;
                }
                evenCondition.signal(); // Wake up even thread
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    // Using synchronized
//    private static void printEven() {
//        while(counter <= MAX_NUM){
//            synchronized (lockObject){
//                if(counter%2==0){
//                    System.out.println(counter);
//                    counter++;
//                    lockObject.notify();  // notify other thread to print its odd number which is waiting
//                }else{
//                    // if counter is odd we will wait till other thread prints its odd number and notify us
//                    try {
//                        lockObject.wait();
//                    }catch (InterruptedException e){
//                        Thread.currentThread().interrupt();
//                    }
//                }
//            }
//        }
//    }
//
//    private static void printOdd(){
//        while(counter <= MAX_NUM){
//            synchronized (lockObject){
//                if(counter%2!=0){
//                    System.out.println(counter);
//                    counter++;
//                    lockObject.notify();  // notify other thread to print its even number which is waiting
//                }else{
//                    // if counter is even we will wait till other thread prints its odd number and notify us
//                    try {
//                        lockObject.wait();
//                    }catch (InterruptedException e){
//                        Thread.currentThread().interrupt();
//                    }
//                }
//            }
//        }
//    }

}
