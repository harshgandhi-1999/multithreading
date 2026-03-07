package questions.printchar;

import questions.printnum.PrintCounter;

public class PrintCharacter {

    private int turn = 1;
    private int counter = 1;
    private final int maxCounter;

    public PrintCharacter(int maxCounter) {
        this.maxCounter = maxCounter;
    }

    public synchronized void printChar(int threadId, char threadChar) {
        while (counter<=maxCounter){

            while(counter<=maxCounter && turn!=threadId){
                try {
                    wait();
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }


            if(counter<=maxCounter){
                System.out.println("Thread " + threadId + " : " + threadChar);
                turn = (turn%3) + 1;
                counter++;
                notifyAll();
            }
        }

    }
}
