package clients;

import data.IBankUsers;
import model.Users;
import org.postgresql.util.PSQLException;

import java.util.List;

public class UsersService {
    private IBankUsers<Users, String> users ;

    //constructor
    public  UsersService(IBankUsers<Users, String> users) {
         this.users = users;
    }

    public List<Users> getAllUsers(){
        return this.users.findAll();
    }


    public boolean authenticate (String email, String pinNumber){
        return this.users.authenticate(email, pinNumber);
    }
    public boolean save(Users obj, String field){
        boolean success = false;
        success = this.users.save(obj, field);
        return success;
    }
    public Users  findUserByEmail(String email){
        Users user = this.users.findByEmail(email);
        return user;
    }


    public boolean insert(Users newObj) throws PSQLException {
        boolean success = false;
        success = this.users.insert(newObj);
        return success;
    }
}
