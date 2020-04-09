package proconexample;

public class Producer extends Thread {
	private Container container;
	
	public Producer(Container c) {
		container = c;
	}

	public void run() {
			for ( int i = 0; i < 5; i++ ) {
				container.putIn(i);
				System.out.println("Producer put value " + i + " into Container.");
				try {
					sleep(2000);
				} catch (InterruptedException intEx) {
					intEx.printStackTrace();
				}
			}
	}
}
