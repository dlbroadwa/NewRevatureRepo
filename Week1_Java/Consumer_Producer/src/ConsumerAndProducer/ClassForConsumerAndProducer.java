package ConsumerAndProducer;

import java.util.LinkedList;
import java.util.Queue;

public class ClassForConsumerAndProducer {

    Queue<Integer> queue;
    int max =15;

    int size;

    public  ClassForConsumerAndProducer(int size){

        this.size = size;
        this.queue = new LinkedList<>();
    }

    public synchronized void producer() throws InterruptedException {
        int value=0;
        while(true)
        {
            while(queue.size()>=size){ // The space is full, wait for Consumer to grab some before Add
                wait();
//            System.out.println("Producer Value while wait "+ queue.size());
            }
            queue.add(value++);
            System.out.println("Producer Value after Adding "+ queue.size());


            notify();
            Thread.sleep(100);
        }

    }


    public synchronized void consumer() throws InterruptedException {

       while(true)
       {
           while (queue.size()==0){// it has enough Space Producer can Add
               wait();
           }
           Object val = queue.remove();
           System.out.println("Consumer :" + val);
           notify();
           Thread.sleep(500);
       }
    }

}
