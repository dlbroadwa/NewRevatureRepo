package proconexample;

public class Container {
	private int contents;
	private boolean available = false;
	
	public synchronized void putIn(int value) {
		while (available) {
			try { 
				wait();
			} catch ( InterruptedException intEx ) {
				intEx.printStackTrace();
			}
		}
		contents = value;
		available = true;
		notifyAll();
		System.out.println("New value " + contents + " put in.");
	}
	
	public synchronized int takeOut() {
		while (!available) {
			try { 
				wait();
			} catch ( InterruptedException intEx ) {
				intEx.printStackTrace();
			}
		}
		available = false;
		notifyAll();
		System.out.println("Value " + contents + " taken out.");
		return contents;
	}
}
