import app.Application;
import app.TimeSheetApp;

/**
 * Project 0
 * Created By; Reginald Jefferson
 * <p>
 * This is the Simple Time Sheet app that allows an admin to enter, remove, and update employees listed in addition to managing their entered hours and salary.
 * Main runs the Time Sheet application itself.
 */
public class Main {
    public static void main(String[] args) {
        Application tsApp = new TimeSheetApp();
        tsApp.runApp();
    }
}
