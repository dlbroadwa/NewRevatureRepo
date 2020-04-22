package com.ex;

import com.ex.clients.UserService;
import com.ex.data.UserSQLRepository;
import com.ex.data.Repository;
import com.ex.models.User;
import com.ex.utils.ConnectionUtils;
import com.ex.utils.PostgresConnectionUtil;
import sun.security.x509.InvalidityDateExtension;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Connection connection;

        String username = System.getenv("MARKETPLACE_USER");
        String password = System.getenv("MARKETPLACE_PASSWORD");
        ConnectionUtils connectionUtils = new PostgresConnectionUtil(
                "jdbc:postgresql://apr06-2004-java.c9ympmujpozd.us-east-1.rds.amazonaws.com:5432/postgres",
                username, password, "public"); // configuration should be store outside of code


        Repository<User, Integer> userRepo = new UserSQLRepository(connectionUtils);
        UserService service = new UserService(userRepo);
        Scanner in = new Scanner(System.in);

        Boolean firstrun = true;

        System.out.println("Welcome to the user login system");
        System.out.println("Enter an option to begin");
        System.out.println("1: Add a new user");
        System.out.println("2: Delete a user");
        System.out.println("3: List all users");
        System.out.println("4: Exit the system");
        String selection = in.next();
        while(!selection.equals("4")) {

            while (!firstrun) {
                System.out.println("Welcome to the user login system");
                System.out.println("Enter an option to begin");
                System.out.println("1: Add a new user");
                System.out.println("2: Delete a user");
                System.out.println("3: List all users");
                System.out.println("4: Exit the system");
                selection = in.next();
            }

            firstrun = false;

            try {
                int entered = Integer.parseInt(selection);
                if (entered < 1 || entered > 4)
                    System.out.println("Invalid choice enter a number between 1 and 3.");
                else {
                    switch (entered) {
                        case 1:
                            System.out.println("You want to add a new user");
                            try{
                                connection = connectionUtils.getConnection();
                                String schemaName = connectionUtils.getDefaultSchema();
                                System.out.println("Enter the new users email");
                                String email = in.nextLine();
                                System.out.println("Enter a password");
                                String pass = in.nextLine();
                                String sql = "Insert into public.user(password, email) values('" + pass + "', '" + email + "');";
                                Statement statement = connection.createStatement();

                            }catch (SQLException sqle) {
                                sqle.printStackTrace();
                            }

                            break;
                        case 2:
                            System.out.println("You want to delete a user from the system");
                            try{
                                connection = connectionUtils.getConnection();
                                String schemaName = connectionUtils.getDefaultSchema();
                                System.out.println("Enter the email of the user you would like to remove:");
                                String email = in.next();
                                String sql = "delete from public.user where email = " + email + ";";
                                Statement statement = connection.createStatement();

                            }catch (SQLException sqle) {
                                sqle.printStackTrace();
                            }
                            break;
                        case 3:
                            System.out.println("Listing all users");
                            List<User> allUsers = service.getAllUsers();
                            for (User  u : allUsers) {
                                System.out.println("User " + u.getEmail());
                            }
                            break;
                        case 4:
                            System.out.println("Ending the program.");
                    }
                }
            } catch (NumberFormatException n) {
                System.err.println("You didn't enter a valid number.");
            }
        }

    }
}