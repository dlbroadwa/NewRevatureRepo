package app;

public abstract class Application {
	
	protected String title;
	
	// What does run do?
	public abstract void run();
	
	public String getTitle() {
		return title;
	}
	
	private void setTitle(String title) {
		this.title = title;
	}
}
