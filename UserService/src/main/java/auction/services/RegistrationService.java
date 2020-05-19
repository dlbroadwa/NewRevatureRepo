package auction.services;

import auction.dataaccess.UserDAO;
import auction.models.User;

import java.security.PrivateKey;

public class RegistrationService {
    private PasswordHasher passwordHasher= null;
    private UserDAO userDAO = null;
    public boolean createUser(String name, String password){
        String hashedPassword = null;
        passwordHasher = new PasswordHasher();
        userDAO = new UserDAO();
        if (password!= null) {
            hashedPassword = passwordHasher.hashPassword(password);
        }
        else {
            System.out.println("Password cannot be null");
            return false;
        }
        return userDAO.save(new User(name, hashedPassword));
    }
}
