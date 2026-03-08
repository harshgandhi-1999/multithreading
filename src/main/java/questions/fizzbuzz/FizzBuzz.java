package questions.fizzbuzz;

class FizzBuzz {
    private int n;
    private int counter = 1;

    public FizzBuzz(int n) {
        this.n = n;
    }

    public void fizz() throws InterruptedException {
        synchronized(this){
            while(counter<=n){

                while(counter<=n && !(counter%3==0 && counter%5!=0)){
                    wait();
                }

                if(counter<=n){
                    System.out.println("Thread1 printing: fizz");
                    counter++;
                    notifyAll();
                }

            }
        }
    }

    public void buzz() throws InterruptedException {
        synchronized(this){
            while(counter<=n){

                while(counter<=n && !(counter%3!=0 && counter%5==0)){
                    wait();
                }

                if(counter<=n){
                    System.out.println("Thread2 printing: buzz");
                    counter++;
                    notifyAll();
                }

            }
        }
    }

    public void fizzbuzz() throws InterruptedException {
        synchronized(this){
            while(counter<=n){

                while(counter<=n && !(counter%3==0 && counter%5==0)){
                    wait();
                }

                if(counter<=n){
                    System.out.println("Thread3 printing: fizzbuzz");
                    counter++;
                    notifyAll();
                }

            }
        }
    }

    public void number() throws InterruptedException {
        synchronized(this){
            while(counter<=n){

                while(counter<=n && !(counter%3!=0 && counter%5!=0)){
                    wait();
                }

                if(counter<=n){
                    System.out.println("Thread4 printing: " + counter);
                    counter++;
                    notifyAll();
                }

            }
        }
    }
}