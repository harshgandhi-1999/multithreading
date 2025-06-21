package virtualthreads;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException{
        // if we create millions of threads and try to run , all thread cannot run since our cpu resources are limited
        // and this thread is bind to the OS but virtual threads are not bind to OS
        // so we use virtual threads

        final int threadCount = 10000;
        List<Thread> threadList = new ArrayList<>();

        Runnable runnable = () -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        for(int i=0;i<threadCount;i++){
//            Thread thread = Thread.ofVirtual().unstarted(runnable);
            Thread thread = new Thread(runnable);
            thread.setName("Thread-"+i);
            thread.start();

            System.out.println("Thread Number: " + i);
            threadList.add(thread);
        }


        for(Thread thread : threadList){
            thread.join();
            System.out.println("Completed: " + thread.getName());
        }

    }
}