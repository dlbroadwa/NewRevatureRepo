import dev.app.ShelterApplication;

/**
 *  Project 0:<br>
 * <br>
 *  The Main class controls the initialization and test run of the application as a whole from the IDE.
 *    Will mainly serve for manual tests involving ShelterApplication.
 *
 *  <br> <br>
 *  Created: <br>
 *     29 April 2020, Barthelemy Martinon<br>
 *     With assistance from: <br>
 *  Modifications: <br>
 *     29 April 2020, Barthelemy Martinon,    Created class.
 * <br>
 *  @author Barthelemy Martinon   With assistance from: 
 *  @version 21 April 2020
 */

public class Main {

    public Main() {
        ShelterApplication m = ShelterApplication.getInstance();
        m.run();
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Main m = new Main();
    }
}