package reentrantlocks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FairnessLockExample {

    private final Lock lock = new ReentrantLock(true);

    public static void main(String[] args) throws InterruptedException{
        FairnessLockExample fairnessLockExample = new FairnessLockExample();

        Runnable runnable = fairnessLockExample::accessResources;

        for(int i=1;i<20;i++){
            int threadId = i;

            Thread thread = new Thread(runnable, "Thread - " + threadId);
            thread.start();
        }

        // if fair is set to true, the lock will acquired in order in which the thread arrived to acquire the lock.
        // in first come first server basis

        // but in non fair locks any thread can acquire the locks out of waiting.
        // ** It may seem in this example that unfair lock is behaving same as fair lock but internally its not.
        // To exaggerate the unfairness, try:
        //
        // 10+ threads
        //
        // Each repeatedly trying to acquire the lock in a loop
        //
        // Run it on a system with fewer cores than threads
    }

    private void accessResources(){
        System.out.println(Thread.currentThread().getName() + " trying to acquired the lock");
        lock.lock();
        System.out.println(Thread.currentThread().getName() + " acquired the lock");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }finally {
            System.out.println(Thread.currentThread().getName() + " released the lock");
            System.out.println("------------------------------------------------");
            lock.unlock();
        }
    }
}
