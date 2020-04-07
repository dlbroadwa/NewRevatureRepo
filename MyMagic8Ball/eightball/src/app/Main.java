package app;

	import java.util.Random;

	public class Main {

		  public static void main(String[] args) {
		    // Polymorphism is the Application looking like some type of Application (Magic8BallApplication or
		    // OptimisticMagic8BallApplication)
		    Application app = new Magic8BallApplication();
		    // ref type               object type

		    // Abstraction is interfacing without knowing the type
		    app.run();
		    
		  }
		  
	}
