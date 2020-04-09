import java.util.Random;

public class Consumer implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Random rand = new Random();
		
		int numOfApples = rand.nextInt(50);
		
		System.out.println("Trying to eat " + numOfApples + " Apples");
			
		int totalApples = Main.getNumOfApples();
			
		if(totalApples >= numOfApples) {
			
			Main.setNumOfApples(totalApples - numOfApples);
			
		}else {
			
			System.out.println("Not enough Apples");
		
			try {
		    	  
		        Thread.sleep(6000);
		        
		      } catch (InterruptedException ex) {
		      
		       
		        
		      }
			
		}
		
	}

}
