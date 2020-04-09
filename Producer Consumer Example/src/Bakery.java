import java.util.ArrayList;

public class Bakery {
	
	ArrayList<String> bread_basket;
	int capacity;
	
	public Bakery(int capacity) {
		bread_basket = new ArrayList<>();
		this.capacity = capacity;
	}
	
	public void bake() throws InterruptedException {
		while (true) {
			synchronized (this) {
				while (bread_basket.size() == capacity)
					wait();
				
				bread_basket.add("Bread");
				System.out.println("Baking bread...The basket currently has " + bread_basket.size() + " bread in it.");
				notify();
				
				Thread.sleep(1000);
			}
		}
	}
	
	public void sell() throws InterruptedException {
		while (true) {
			synchronized (this) {
				while(bread_basket.size() == 0)
					wait();
				
				bread_basket.remove(0);
				System.out.println("Selling bread...The basket currently has " + bread_basket.size() + " bread in it.");
				notify();
				
				Thread.sleep(1000);
			}
		}
	}
}
