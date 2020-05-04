package Screens;

import clients.UsersService;
import data.BankUsersInSQLRepo;
import data.IBankUsers;
import model.Users;
import utilities.ConnectionUtils;
import utilities.PostgresConnectionUtil;

import java.util.Scanner;

public class CreateUserMenu {
    private boolean manager;
    private String pinNumber;
    private String phoneNumber;
    private String name;
    private String email;

    public CreateUserMenu(){
        this.manager = false;
        this.pinNumber = "default";
        this.phoneNumber = "default";
        this.name = "default";
        this.email = "default";
    }

    public Users requestNewUserDetails(){
        String name = userName();
        String email = requestUserEmail();
        String phone = requestPhoneNumber();
        String pin = requestPin();
        boolean isManager = isUserManager();
        Users tmpUser = null;
        if (email != null && phone != null && pin != null){
            tmpUser = new Users();
            tmpUser.setName(name);
            tmpUser.setEmail_address(email);
            tmpUser.setPhone_number(phone);
            tmpUser.setUser_pin(pin);
            tmpUser.setIs_admin(isManager);
        }


        return tmpUser;
    }

    private boolean isUserManager() {
        Scanner sc = new Scanner(System.in);
        String tmp = null;
        int count = 0;
        boolean manager = false;
        System.out.println("Is new user a bank manager? Enter 'Y' or 'N'");
        do{
            tmp = sc.next();
            tmp = tmp.trim();

            if (tmp.equals("Y") || tmp.equals("y")){
                return true;
            }
            else if (tmp.equals("N") || tmp.equals("n")){
                return false;
            }
            else{
                count++;
                if (count > 4){
                    System.out.println("Bad response entered " + count + " times.  Default to No");
                    return false;
                }
                System.out.println("Bad response.  Is new user a bank manager? Enter 'Y' or 'N'");

            }


        }while (true);


    }

    private String requestPin() {
        Scanner sc = new Scanner(System.in);
        String tmpPin = null;
        int count = 0;
        System.out.println("Please enter your pin (8 character max): ");
        do{
            tmpPin = sc.next();
            if (tmpPin.length() > 8){
                if (count > 5){
                    System.out.println("Bad pin entered " + count + " times.");
                    count++;
                    break;
                }
                System.out.println("Enter pin less than 8 characters: ");
            }
            else{
                break;
            }
        }while (true);

        return tmpPin;

    }

    private String requestPhoneNumber() {
        Scanner sc = new Scanner(System.in);
        final int maxCharacters = 8;
        int number;
        int count = 0;
        String tmpString = null;
        System.out.println("Please enter phone number: ");
        boolean isDigit = true;
        do{
            tmpString = sc.next();
            if (tmpString.length() == 10){
                for (int i = 0; i < tmpString.length(); i++){
                    if (!Character.isDigit(tmpString.charAt(i))){
                        isDigit = false;
                        tmpString = null;
                    }
                }
                if (isDigit){
                    break;
                }
            }
            else{
                count++;
                tmpString = null;
                if (count > 4){
                    System.out.println("Bad number entered " + count + " times.");
                    break;
                }
                System.out.println("Please enter valid phone number (10 digits): ");

            }
        }while (true);
        return tmpString;
    }

    private String requestUserEmail() {
        boolean duplicateEmail = false;
        int count = 0;
        Scanner sc = new Scanner(System.in);
        String tmpStr = null;
        //database connection details
        ConnectionUtils connectionUtils = new PostgresConnectionUtil(
                "jdbc:postgresql://project0nathan.crq2hafydb4f.us-east-2.rds.amazonaws.com:5432/postgres",
                "nathan", "nathannguyen", "myschema");


        IBankUsers<Users, String> IBankUsers = new BankUsersInSQLRepo(connectionUtils) ;
        UsersService usersService = new UsersService(IBankUsers);
        do {
            System.out.println("Enter user email (Will be used as user name): ");

            tmpStr = sc.next();

            Users tmpUser = usersService.findUserByEmail(tmpStr);
            if (tmpStr.equals(tmpUser.getEmail_address())){
                if (count < 5){
                    System.out.println("Duplicate email in system.");
                    count++;
                    break;
                }
                else{
                    System.out.println("Duplicate email entered " + count + " times.");
                    return null;
                }
            }
            else{
                break;
            }
        } while (true);




        return tmpStr;
    }

    private String userName() {
        System.out.println("Please enter customer name: ");
        Scanner sc = new Scanner(System.in);
        String tmp = sc.next();
        return tmp;
    }

    public boolean isManager() {
        return manager;
    }

    public void setManager(boolean manager) {
        this.manager = manager;
    }

    public String getPinNumber() {
        return pinNumber;
    }

    public void setPinNumber(String pinNumber) {
        this.pinNumber = pinNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
