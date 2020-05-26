package service;

import data.UserRepository;
import model.User;
import utils.Encryption;
import utils.Regex;

/**
 * Service providing functionality with User objects and the User Repository
 */
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;

    /**
     * Constructor taking in the UserRepository DAO object
     * @param userRepository UserRepository DAO class to access database
     */
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Authenticates a user's credentials to log them in
     * @param username - String for the username
     * @param password - String for the password
     * @return returns a User object if authenticated by username and password, null if not
     */
    @Override
    public User checkCredentials(String username, String password) {
        User temp = userRepository.findById(username);
        if (temp == null){
            return null;
        }
        else if (!temp.getPassword().equals(password)){
            return null;
        }
        return temp;
    }

    /**
     * Updates a user's password in the database if the new password passes RegEx requirements
     * @param password - String password to be updated to
     * @param user - User object whose password should be updated
     * @return returns a boolean whether operation was successful
     */
    @Override
    public boolean changePassword(String password, User user) {
        if(Regex.isValidPassword(password)){
            user.setPassword(Encryption.encrypt(password));
            userRepository.update(user);
            return true;
        }
        return false;
    }

    /**
     * Changes a user's first name in the database
     * @param first String for the first name
     * @param user User object whose first name is to be changed
     * @return returns boolean whether operation was successful
     */
    @Override
    public boolean changeFirstName(String first, User user) {
        if(Regex.isValidName(first)){
            user.setFirstName(first);
            userRepository.update(user);
            return true;
        }
        return false;
    }

    /**
     * Changes a user's last name in the database
     * @param last String for the last name
     * @param user User object whose last name is to be changed
     * @return returns boolean whether operation was successful
     */
    @Override
    public boolean changeLastName(String last, User user) {
        if(Regex.isValidName(last)){
            user.setLastName(last);
            userRepository.update(user);
            return true;
        }
        return false;
    }

    /**
     * Finds and returns a User from the database by their unique username
     * @param username String username to find a User by
     * @return returns the user object or null if not found
     */
    @Override
    public User findByID(String username) {
        return userRepository.findById(username);
    }

    /**
     * Deletes a User entry from the database
     * @param user User object to be deleted from the database
     */
    @Override
    public void closeAccount(User user) {
        userRepository.delete(user);
    }

    /**
     * Registers a new user by creating an entry in the database
     * @param username String Username for the new user
     * @param password String Password for the new user
     * @param firstName String first name for the new user
     * @param lastName String last name for the new user
     */
    @Override
    public void createAccount(String username, String password, String firstName, String lastName) {
    	String pass = Encryption.encrypt(password);
        userRepository.create(new User(username,pass,firstName,lastName));
    }
}
