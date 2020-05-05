package Screens;

import java.util.Scanner;

public class ManagerViewSingleAccountMenu {


    private String userAccountEmail ;
    public ManagerViewSingleAccountMenu(){
        this.userAccountEmail = "default";
    }
    public void setUserAccountEmail(String userAccountEmail) {
        this.userAccountEmail = userAccountEmail;
    }
    public String getUerAccountEmail() {
        return userAccountEmail;
    }
    public void printRequestAccountId(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter user email:");
        this.setUserAccountEmail(sc.next());
    }
}
