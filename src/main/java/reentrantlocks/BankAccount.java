package reentrantlocks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    
    private int balance = 100;

    private final Lock lock = new ReentrantLock();

    public void withdraw(int amount) {

        System.out.println(Thread.currentThread().getName() + " attempting to withdraw " + amount);
        try {
            // current thread will check if lock is available
            // if not available then current thread will wait for this much time if the lock release
            // there is also a method lock.lock() but we will not use it because it is same as synchronized
            // so thread will wait indefinitely until other thread releases the lock
            if (lock.tryLock(2000, TimeUnit.MILLISECONDS)) {
                if (balance >= amount) {
                    System.out.println(Thread.currentThread().getName() + " proceeding with withdrawal");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        System.out.println("Thread is interrupted!");
                        //Here thread should be interrupted or exception should be thrown, making log is not enough as thread interrupted state will be lost
                        Thread.currentThread().interrupt();
//                        throw new RuntimeException(e);
                    }finally {
                        lock.unlock();
                    }
                    balance -= amount;
                    System.out.println(Thread.currentThread().getName() + " completed withdrawal. Remaining balance: " + balance);
                } else {
                    System.out.println(Thread.currentThread().getName() + " Insufficient balance ");
                }
            }else{
                System.out.println(Thread.currentThread().getName() + " Could not acquire the lock, will try again later");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
//            throw new RuntimeException(e);
        }

        if(Thread.currentThread().isInterrupted()){
            // we can log here if some cleanup is required
            System.out.println("Thread was interrupted");
        }
    }

    public synchronized void withdrawWithSynchronized(int amount){
        System.out.println(Thread.currentThread().getName() + " attempting to withdraw " + amount);
        if(balance >= amount){
            System.out.println(Thread.currentThread().getName() + " proceeding with withdrawal");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + " completed withdrawal. Remaining balance: " + balance);
        }else{
            System.out.println(Thread.currentThread().getName() + " Insufficient balance ");
        }
    }
}
