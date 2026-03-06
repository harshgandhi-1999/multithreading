package questions.easy.evenodd;

public class PrintCounter {

    private int counter = 0;
    private final int maxCounter = 10;

    public synchronized void printEven(){
        while (counter<=maxCounter){

            // always ensure wait() is inside while loop so that when threads wakes up consition is again checked.
            while(counter%2!=0){
                try {
                    wait();
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }

            if(counter<=maxCounter){
                System.out.println("Print Even: " + counter);
                counter++;
                notifyAll();  // always use notifyall instead of notify to wake up all threads so that they compete for the lock
            }
        }
    }

    public synchronized void printOdd(){
        while (counter<=maxCounter){
            while(counter%2==0){
                try {
                    wait();
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }

            // we have to check it again when the thread awakes before incrementing and printing
            // because lets say even method has printed 10 ans increment counter to 11
            // and after that this thread wakes up , it will print 11 so we have to put this condition here
            if(counter<=maxCounter){
                System.out.println("Print Odd: " + counter);
                counter++;
                notifyAll();
            }

        }
    }
}
