package com.threads;

import java.util.LinkedList;

public class Consumer implements Runnable {
	private LinkedList<Integer> buffer;
	private int consumeSize = 3;
	
	public Consumer(int consumeSize, LinkedList<Integer> buffer){
		this.buffer = buffer;
		this.consumeSize = consumeSize;
	}
	
	@Override
	public void run() {
		int i = 0;
		while(i < consumeSize) {
			synchronized(buffer) {
				if(buffer.size() == 0) {
					try {
						System.out.println("Buffer empty. Consumer waiting.");
						buffer.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					System.out.println("The Consumer consumed: " + buffer.removeFirst());
					i++;
					buffer.notify();
				}
			}
		}
		
	}

}
