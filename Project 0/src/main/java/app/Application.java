package app;

public abstract class Application {
    protected String title;
    public abstract void runApp();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
