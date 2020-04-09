package coumsumer;

import java.util.LinkedList;
import java.util.Queue;

public class myTreads implements Runnable {

    Queue queue ;
    int max =15;
    int size;

    public myTreads(int size){
        this.size = size;
        queue = new LinkedList<Object>(); // queue LinkedList is created to Store data
    }

    @Override
    public void run() { // override the Run Method from Runnable Interface
    for (int i = 1 ; i<size+1  ; i++)
        {
            try {
                // passing data to the method
                Producer(i); // Adding
                Consumer(i); // remove

                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private Object notFull = new Object(); // create object to call Thread method function
    private Object full = new Object();

    private synchronized void Producer(Object e) throws InterruptedException {

    while(queue.size() == max){
        notFull.wait();
    }
    queue.add(e);
        System.out.println(queue);
   //full.notifyAll();

    }

    private synchronized void Consumer (Object e) throws InterruptedException {
        if (queue.size() == 0){
            full.wait();
        }
       Object item= queue.remove();
        System.out.println(item);
      //  notFull.notifyAll();

    }
}
