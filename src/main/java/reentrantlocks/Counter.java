package reentrantlocks;

public class Counter {

    private int count = 0;
    public synchronized void increment(){
        // synchronized is intrinsic locks
        count++;
    }

    public int getCount(){
        return count;
    }
}
