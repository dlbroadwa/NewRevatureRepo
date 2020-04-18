package com.proj.app;
import com.proj.clients.EventServices;
import com.proj.clients.UserServices;
import com.proj.data.EventSQLRepository;
import com.proj.data.Repository;
import com.proj.data.UserSQLRepository;
import com.proj.models.Event;
import com.proj.models.User;
import com.proj.screens.LoginScreen;
import com.proj.screens.Screen;
import com.proj.screens.WelcomeScreen;
import com.proj.utils.ConnectionUtils;
import com.proj.utils.PostgresConnectionUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;


public class EventHandler {

    private String username;
    private String password;

    private Scanner scanner;
    private Screen currentScreen = null;

//**********************************   [Connects to the AWS RDB]    **********************************************//
    private ConnectionUtils connectionUtils = new PostgresConnectionUtil(
            "jdbc:postgresql://revatureproject-0.cxeo5zs5fqav.us-east-2.rds.amazonaws.com:5432/postgres",
            "johnproj0", "JellyBean13", "revproj0");
//****************************************************************************************************************//

    Repository<Event, Integer> eventRepo = new EventSQLRepository(connectionUtils);
    Repository<User, String>  userRepo = new UserSQLRepository(connectionUtils);

    EventServices eService = new EventServices(eventRepo);
    UserServices uService = new UserServices(userRepo);

    public EventHandler() {
        currentScreen = new LoginScreen();
        this.scanner = new Scanner(System.in);

    }

    public void run() throws IOException, SQLException {
        while(currentScreen != null) {
            currentScreen = currentScreen.doScreen(this);


        }
    }

    public Scanner getScanner() {
        return scanner;
    }

    public UserServices getUService() { return uService; }
    public EventServices getEService() { return eService; }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
