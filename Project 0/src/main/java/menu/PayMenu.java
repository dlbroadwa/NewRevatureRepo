package menu;

import java.util.Scanner;

public class PayMenu extends Menu {
    //submenu for accessing pay
    private static boolean returnM;
    //public function for returning input
    public static void runPayM(){
        payMenu();
        while(!returnM){
            payMenu();
            int choice = getInput();
        }
    }
    private static void payMenu(){
        System.out.println("What would you like to do?");
        System.out.println("1:\t View Total Hours Worked");
        System.out.println("2:\t View Gross Pay");
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
                //code to be entered to view sum of hours
                break;
            case 2:
                //code to be entered to view gross pay(hours x salary)
                break;
            default:
                System.out.println("An issue has occurred :(");
        }

    }
}
