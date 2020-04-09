
public class Main {
	
	public static void main(String[] args) {
		
		Bakery bakery = new Bakery(5);
		
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					bakery.bake();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				try {
					bakery.sell();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		});
		
		t1.start();
		t2.start();
	}
}
