package menu;

import app.Application;
import app.TimeSheetApp;

import java.util.Scanner;

public class MainMenu implements Menu{
    //contains com.main menu
    boolean exit;
    private HoursMenu hMenu = null;
    private PayMenu pMenu = null;


    @Override
    public void makeMenu(Application app) {
        Scanner scanner = ((TimeSheetApp)app).getScanner();
        printHeader();
        while(!exit){
            mainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            menuAction(choice);
        }
    }
//    private static boolean exit;

/*    public static void runMenu(){
        printHeader();
        while(!exit){
            showMenu();
            int choice = getInput();
        }
    }*/

    private void printHeader(){
        System.out.println("Welocome to Timesheets!");
    }

    static Application mainMenu(){
        System.out.println("Please make a selection: \n");
        System.out.println("1:\t Weekly Hours");
        System.out.println("2:\t Weekly Pay");
        System.out.println("0:\t Exit");
        //return null;
        return null;
    }

    /*private static int getInput(){
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
    //Menu with options to access submenus
    private Menu menuAction(int choice){
        switch (choice){
            case 0:
                exit = true;
                System.out.println("Thanks for using Timesheets!");
                System.exit(0);
                break;
            case 1:
                hMenu = new HoursMenu();
                HoursMenu.hoursMenu();
                HoursMenu.menuAction();
                //return new HoursMenu.runHoursM();
                break;
            case 2:
                //PayMenu.payMenu();
                //pMenu = new PayMenu();
                break;
            default:
                System.out.println("That isn't a valid menu option.\n");
                return (Menu) MainMenu.mainMenu();
        }
        return null;
    }

/*    private HoursMenu hoursMenu() {
        HoursMenu hoursMenu = new HoursMenu();
        return hoursMenu;
    }
    private PayMenu payMenu() {
        PayMenu payMenu = new PayMenu();
        return payMenu;
    }*/
}
