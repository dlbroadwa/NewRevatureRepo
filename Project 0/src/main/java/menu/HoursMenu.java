package menu;

import app.Application;
import app.TimeSheetApp;

import java.util.Scanner;

/**
 * Menu specific to utilizing Timesheet and Employee Database and related functions. Issues occurred with properly implementing
 * Hours Menu with main menu. Unused for now.
 */

public class HoursMenu implements Menu/*extends Menu */{
    //submenu for accessing hours
    static boolean returnM;
    private Object HoursMenu;

    public HoursMenu() {
    }

    @Override
    public Menu makeMenu(Application app) {
        Scanner scanner = ((TimeSheetApp)app).getScanner();
        while(!returnM){
            hoursMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            menuAction(choice);
        }
        return (Menu) HoursMenu;
    }
    public static void menuAction() {
    }
    //public function for returning input
/*    public static MainMenu runHoursM(){
        hoursMenu();
        while(!returnM){
            hoursMenu();
            int choice = getInput();
        }
    }*/
    static void hoursMenu(){
        System.out.println("Please make a selection: ");
        System.out.println("1:\t View Hours For the Week");
        System.out.println("2:\t Enter Hours");
        System.out.println("0:\t Return to Main Menu");
        //return null;
    }
/*    private static int getInput(){
        Scanner in = new Scanner(System.in);
        int choice = -1;
        while (choice !=0*//*choice < 0 || choice>2*//*) {
            try {
                System.out.println("Enter a choice: ");
                choice = Integer.parseInt(in.nextLine());
            } catch (NumberFormatException n){
                System.out.println("Invalid selection. Try again please.");
            }
        }
        return choice;
    }*/
    private Menu menuAction(int choice){
        switch (choice){
            case 0:
                returnM = true;
                return new MainMenu();
            case 1:
                //code to be inserted later for viewing weekly hours
                System.out.println("Here are your hours: ");

                break;
            case 2:
                //code to be inserted later for entering hours into database
                break;
            default:
                System.out.println("That isn't a valid menu option.\n");
//                return HoursMenu.hoursMenu();
        }
        return (Menu) HoursMenu;
    }
}
