package com.ex;


import java.util.LinkedList;
import java.util.Scanner;

import com.threads.Consumer;
import com.threads.Producer;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int bufferSize;
		int producedItems;
		LinkedList<Integer> buffer = new LinkedList<Integer>();
		/*buffer.add(1);
		buffer.add(2);
		buffer.add(3);*/
		
		System.out.println("How many numbers would you like to produce?");
		producedItems = scanner.nextInt();
		System.out.println("How big do you want the buffer to be?");
		bufferSize = scanner.nextInt();
		
		Thread producer = new Thread(new Producer(producedItems,bufferSize,buffer));
		Thread consumer = new Thread(new Consumer(producedItems,buffer));
		
		producer.start();
		consumer.start();
	}

}
