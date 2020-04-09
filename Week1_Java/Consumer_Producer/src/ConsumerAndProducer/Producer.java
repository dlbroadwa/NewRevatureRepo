package ConsumerAndProducer;

import java.util.LinkedList;
import java.util.Queue;

public class Producer implements Runnable {

    Queue queue;
    int max =5;
    int size;
    Object object;

    Object full = new Object();
    Object notFull = new Object();

    public Producer(int size){
       // this.object = objects;
        this.size = size;
        queue = new LinkedList<Object>();
    }
    @Override
    public void run() {
        try{
            Thread.sleep(100);
//            object = new Object();

            for(int i =0 ; i<size ; i++) {
                AddToArray(i);

            }
            System.out.println("Producer Size is now At TRY:" + queue.size());
        }catch (InterruptedException ex){
            System.out.println(ex.getMessage());
            System.out.println("Producer is Intercepted");
            System.out.println("Producer Size is now At Catch:" + queue.size());
        }

    }


    public synchronized void AddToArray(Object obj) throws InterruptedException {

        // Not Full You can Add
        while(queue.size() == max){
            wait();
        }

        // Add Job to the List Queue
        queue.add(obj);
      //  System.out.println("After Adding"+queue);
       notifyAll();

    }
}
