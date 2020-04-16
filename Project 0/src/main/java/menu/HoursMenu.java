package menu;

import java.util.Scanner;

public class HoursMenu extends Menu {
    //submenu for accessing hours
    private static boolean returnM;
    //public function for returning input
    public static void runHoursM(){
        hoursMenu();
        while(!returnM){
            hoursMenu();
            int choice = getInput();
        }
    }
    private static void hoursMenu(){
        System.out.println("Please make a selection: ");
        System.out.println("1:\t View Hours For the Week");
        System.out.println("2:\t Enter Hours");
        System.out.println("0:\t Return to Main Menu");
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
    private void menuAction(int choice){
        switch (choice){
            case 0:
                returnM = true;
                Menu.runMenu();
                break;
            case 1:
                //code to be inserted later for viewing weekly hours
                break;
            case 2:
                //code to be inserted later for entering hours into database
                break;
            default:
                System.out.println("An issue has occurred :(");
        }

    }
}
