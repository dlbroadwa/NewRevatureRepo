package auction.services;

import auction.dataaccess.DAO;
import auction.dataaccess.UserDAO;
import auction.models.User;


import java.util.ArrayList;
import java.util.List;

public class UserService {
    private final UserDAO userDao;

    public UserService(UserDAO dao) {
        this.userDao = dao;
    }

    /**
     *Attempt to login the passed username/password to the database
     * @param username
     * @param password
     * @return - User object of logged in user - null if fails
     */
    public User loginUser(String username, String password) {
        User user = null;
        List<User> userList = new ArrayList<>();
        userList = userDao.retrieveAll();
        //System.out.println("Looking for user: " + username + " with a password of: " + password);
        for (User users: userList) {
            //System.out.println(users);
            if (users.getUserName().equals(username)) {
                //System.out.println("Found a match: " + users);
                user = userDao.retrieveByID(users.getUserId());
                break;
            }
        }
        String createTable = "CREATE TABLE if not exists ebay_schema.session ("
                +  "sessionid serial PRIMARY KEY,"
                +  "userid serial REFERENCES ebay_schema.users(userid) UNIQUE"
                +  ");";
        userDao.statement(createTable);
        Boolean loggedIn = userDao.getSession(user.getUserId());
        if (!loggedIn) {
            String insertUser = "INSERT INTO ebay_schema.session(userid) VALUES ('" + user.getUserId() + "');";
            userDao.statement(insertUser);
        }
        return user;
    }

    public boolean logout(int sessionId){
        int loggedOut = 0;
        boolean worked = false;
        if (sessionId>0) {
            String logoutString = "DELETE FROM  ebay_schema.session WHERE sessionid = " + sessionId + ";";
            userDao.statement(logoutString);
//            try{
//                System.out.println(logoutString);
//            }catch (SQLException e){
//                e.printStackTrace();
//            }
        }
        if (loggedOut==0){
            return false;
        }
        else {
            return true;
        }
    }

    public boolean registerUser(String name, String password){

        return userDao.save(new User(name, password));
    }
}