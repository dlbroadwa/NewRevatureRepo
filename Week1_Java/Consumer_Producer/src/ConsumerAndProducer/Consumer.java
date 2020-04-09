package ConsumerAndProducer;

import java.util.LinkedList;
import java.util.Queue;

public class Consumer implements Runnable {

    Queue queue;
    int max =15;
    Object object;
    int capacity;

    private Object full = new Object();
    private Object notFull = new Object();

    public Consumer(int capacity){

       // this.object =objects;

        this.capacity = capacity;
        queue = new LinkedList<Object>();

    }


    @Override
    public void run() {


        try {
            Thread.sleep(1000);

           Taker();
            System.out.println("Consumer Size is now At TRY:" + queue.size());
        } catch (InterruptedException e) {
            e.printStackTrace();

            System.out.println("Consumer Size is now :" + queue.size());
        }
    }
int val =0;
    public  synchronized void Taker() throws InterruptedException {

        while (queue.size() == 0){
            wait();
        }


        Object value = queue.add(val++);
      //  System.out.println("consumer" + value);

        notifyAll();

    }

}
