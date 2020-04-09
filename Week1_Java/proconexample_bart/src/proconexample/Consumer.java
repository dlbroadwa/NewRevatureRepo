package proconexample;

public class Consumer extends Thread {
	private Container container;
	
	public Consumer(Container c) {
		container = c;
	}

	public void run() {
			int value = 0;
			for ( int i = 0; i < 5; i++ ) {
				value = container.takeOut();
				System.out.println("Consumer took value " + value + " from Container.");
				try {
					sleep(2000);
				} catch (InterruptedException intEx) {
					intEx.printStackTrace();
				}
			}
	}
}
