import auction.services.UserService;

public class Main {

    public static void main(String[] args){

        UserService us = new UserService();

        us.loginUser("JohnCena", "WWE4EVA");
    }

}
