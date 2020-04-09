package Application;

import java.util.ArrayList;
import java.util.Scanner;

public class ATMApplication {
    private Scanner scan = null;

    public void run() {
        try {
            scan = new Scanner(System.in);
            // TODO
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scan.close();
        }
    }
}
