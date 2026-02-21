package customlock;

public class CustomLock{
    private boolean isLocked = false;

    public synchronized void lock() throws InterruptedException {
        while (isLocked){
            // now this thread will go into waiting state because already some other thread has acquired the lock
            wait();
        }
        isLocked = true;
    }

    public synchronized void unlock(){
        isLocked = false;
        notifyAll(); //notify all other threads to wake up
    }
}
