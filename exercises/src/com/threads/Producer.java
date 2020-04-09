package com.threads;


import java.util.LinkedList;

public class Producer implements Runnable {
	private LinkedList<Integer> buffer;
	private int size = 3;
	private int producedItems;
	
	public Producer (int producedItems, int size, LinkedList<Integer> buffer){
		this.buffer = buffer;
		this.size = size;
		this.producedItems = producedItems;
	}
	
	@Override
	public void run() {
		int producedInt = 1;
		while(producedInt <= producedItems) {
			//System.out.println("Producing");
			synchronized(buffer) {
				if(buffer.size() == size) {
					try {
						System.out.println("Buffer full. Producer waiting.");
						buffer.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if(producedInt <= producedItems){
						buffer.add(producedInt);
						System.out.println("The Producer produced: " + producedInt++);
						buffer.notify();
						
				}
			}
		}
	}

}
