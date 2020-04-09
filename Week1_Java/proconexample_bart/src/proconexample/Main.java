package proconexample;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Container c = new Container();
		Producer pro = new Producer(c);
		Consumer con = new Consumer(c);
		
		pro.start();
		con.start();
		
//		try {
//			pro.join();
//			con.join();
//		} catch ( InterruptedException intEx ) {
//			intEx.printStackTrace();
//		}
		
	}

}
