
public class Main {

	private static int numOfApples;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Thread produce = new Thread(new Producer());
		
		Thread consume = new Thread(new Consumer());
		
		produce.start();
		
		consume.start();
		
	}
	
	public synchronized static int getNumOfApples() {
		
		return numOfApples;
	
	}
	
	public synchronized static void setNumOfApples(int num) {
		
		if(num < 0) {
			
			System.out.println("There are no apples");
		
			return;
		}
		
		if(numOfApples > 200) {
			
			System.out.println("The basket is full");
			
			return;
		}
		
		numOfApples = num;
		
		System.out.println("There are " + numOfApples + " left");
	}

}
