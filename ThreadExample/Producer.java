import java.util.Random;

public class Producer implements Runnable {
	
	private int numOfApples;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		Random rand = new Random();
		
		numOfApples = rand.nextInt(100);
		
		System.out.println("Growing " + numOfApples + " Apples");
		
		      try {
		    	  
		        Thread.sleep(3000);
		        
		      } catch (InterruptedException ex) {
		      
		        return;
		        
		      }
		      
		    numOfApples +=  Main.getNumOfApples();
		    
		    	Main.setNumOfApples(numOfApples);
		    	
		 
		      numOfApples = 0;
		      
	}
	
}
