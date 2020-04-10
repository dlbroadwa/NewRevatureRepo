package coumsumer;

import ConsumerAndProducer.ClassForConsumerAndProducer;
import ConsumerAndProducer.Consumer;
import ConsumerAndProducer.Producer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    public static void main(String[] args) throws InterruptedException {

//        System.out.println("testing");
//        // Create the threads and calling the constructor of Mythreads with its argument
//        Thread app = new Thread(new myTreads(4));
//       // Starting the Thread Application
//        app.start();
//        System.out.println("testing 2");
//        Queue<Object> objects = new LinkedList<Object>();
       // BlockingQueue<Object> objects = new LinkedBlockingQueue<>(10);

//        for(int i = 0 ; i<16 ; i++) {
//
//           Thread consumer = new Thread(new Consumer(i));
//
//    consumer.start();
//
//        }
//
//        for(int i = 0 ; i<16 ; i++) {
//
//           Thread Producer=  new Thread(new Producer(i));
//
//            Producer.start();
//
//        }

        ClassForConsumerAndProducer bothAction = new ClassForConsumerAndProducer(3);

        Thread ProducerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    bothAction.producer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
            }
        });


        Thread ConsumerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    bothAction.consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
            }
        });

        ProducerThread.start();
        ConsumerThread.start();

        ProducerThread.join();
        ConsumerThread.join();

    }
}
