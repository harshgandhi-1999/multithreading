package questions.printnum;

public class PrintCounter {
    private int counter = 1;
    private final int maxCounter;

    private int turn = 1;

    public PrintCounter(int n) {
        this.maxCounter = n;
    }

    public synchronized void printCounter(int threadId) {
        while (counter <= maxCounter) {

            // if we will not put condition here again for checking counter, the program may not end
            // because there may be one thread goes into waiting state and not other thread to wake that up.
            while (counter <= maxCounter && turn != threadId) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            if (counter <= maxCounter) {
                System.out.println("Thread " + threadId + " : " + counter);
                counter++;
                turn = (turn) % 3 + 1;
                notifyAll();
            }
        }
    }
}

    // Instead of creating 3 different methods as below for 3 different threads and writing redundant code, we will
    // generalize and make a single method as above
//    public synchronized void t1PrintCounter(){
//        while(counter <= maxCounter){
//
//            while(turn!=1){
//                try{
//                    wait();
//                }catch (InterruptedException e){
//                    Thread.currentThread().interrupt();
//                }
//            }
//
//            if(counter<=maxCounter){
//                System.out.println("Thread1: " + counter);
//                counter++;
//                turn = 2;
//                notifyAll();
//            }
//        }
//    }