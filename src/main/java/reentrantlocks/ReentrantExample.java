package reentrantlocks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantExample {
    private final Lock lock = new ReentrantLock();


    public static void main(String[] args) {
        ReentrantExample reentrantExample = new ReentrantExample();

        reentrantExample.outerMethod();
    }

    public void outerMethod(){

        lock.lock();

        try {
            System.out.println("Outer method");
            innerMethod();
        }finally {
            lock.unlock();  // finally 2nd time released lock so that another thread can enter
        }
    }

    private void innerMethod(){
        // if we are thinking deadlock will happen since same thread is waiting for its lock to release
        // deadlock will not happen since we are using Reentrant lock
        // A count is maintained that how many times a lock is acquired
        // Here the lock is acquired again - 2 times
        lock.lock();
        try {
            System.out.println("Inner method");
        }finally {
            lock.unlock();   // first time unlocked lock , here also another thread cannot enter inside outermethod
        }
    }
}
