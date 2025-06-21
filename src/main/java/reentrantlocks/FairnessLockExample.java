package reentrantlocks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FairnessLockExample {

    private final Lock lock = new ReentrantLock(true);

    public static void main(String[] args) {
        FairnessLockExample fairnessLockExample = new FairnessLockExample();

        Runnable runnable = fairnessLockExample::accessResources;

        Thread thread1 = new Thread(runnable, "Thread-1");
        Thread thread2 = new Thread(runnable, "Thread-2");
        Thread thread3 = new Thread(runnable, "Thread-3");

        thread1.start();
        thread2.start();
        thread3.start();
    }

    private void accessResources(){
        lock.lock();
        System.out.println(Thread.currentThread().getName() + " acquired the lock");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }finally {
            System.out.println(Thread.currentThread().getName() + " released the lock");
            lock.unlock();
        }
    }
}
