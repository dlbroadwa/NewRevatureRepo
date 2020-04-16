package menu;

import java.util.Scanner;

public class Menu {
    //contains com.main menu
    private static boolean exit;

    public static void runMenu(){
        printHeader();
        while(!exit){
            showMenu();
            int choice = getInput();
        }
    }

    private static void printHeader(){
        System.out.println("Welocome to Timesheets!");
    }

    private static void showMenu(){
        System.out.println("Please make a selection: ");
        System.out.println("1:\t Weekly Hours");
        System.out.println("2:\t Weekly Pay");
        System.out.println("0:\t Exit");
    }

    private static int getInput(){
        Scanner in = new Scanner(System.in);
        int choice = -1;
        while (choice !=0/*choice < 0 || choice>2*/) {
            try {
                System.out.println("Enter a choice: ");
                choice = Integer.parseInt(in.nextLine());
            } catch (NumberFormatException n){
                System.out.println("Invalid selection. Try again please.");
            }
        }
        return choice;
    }
    //Menu with options to access submenus
    private Menu menuAction(int choice){
        switch (choice){
            case 0:
                exit = true;
                System.out.println("Thanks for using Timesheets!");
                break;
            case 1:
                //HoursMenu.runHoursM();
               //return new HoursMenu.runHoursM();
                break;
            case 2:
                //PayMenu.runPayM();
                //return new PayMenu.runPayM();
                break;
            default:
                System.out.println("An issue has occurred :(");
                return new Menu();
        }
        return null;
    }
}
