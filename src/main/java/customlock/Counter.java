package customlock;

import java.util.concurrent.locks.Lock;

public class Counter {

    private int count = 0;

    private CustomLock lock = new CustomLock();

    public void increment() throws InterruptedException {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    public int getCount() {
        return count;
    }
}
