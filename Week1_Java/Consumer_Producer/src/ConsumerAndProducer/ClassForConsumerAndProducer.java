package ConsumerAndProducer;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ClassForConsumerAndProducer {

    List<String> message;
    int max =15;

    int size;

    public  ClassForConsumerAndProducer(int size){

        this.size = size;
        this.message = new LinkedList<>();
    }

    public synchronized void producer() throws InterruptedException {
        //int value=0;


        while(true)
        {
            while(message.size() == size){ // The space is full, wait for Consumer to grab some before Add
                wait();
                System.out.println("Producer Date Time  FULL");
            }

        String date = LocalDateTime.now().toString();
        message.add(date);

            System.out.println("Producer Date Time "+ date);


            notify();
            Thread.sleep(500);
        }

    }


    public synchronized String consumer() throws InterruptedException {

       while(true)
       {
        notify();
           while (message.size() == 0){// it has enough Space Producer can Add
               wait();
               System.out.println("Consumer Date Time Empty:" );
           }
           String val = message.get(0);
        message.remove(val);
           System.out.println("Consumer Date Time :" + val);

           Thread.sleep(1000);
          // return val;

       }
    }

}
