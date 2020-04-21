package tests;

import com.Project0.users.UserRepository;
import com.Project0.users.Users;
import com.Project0.utilities.PostgresConnectionUtilities;
import org.junit.Test;

import java.util.List;

public class UserTests {
    @Test
    public void userCreate() {
        UserRepository repo = new UserRepository(new PostgresConnectionUtilities());
        repo.update(new Users("625840", "turtles","false"), "625840");
    }

    @Test
    public void login() {
        UserRepository repo = new UserRepository(new PostgresConnectionUtilities());
        System.out.println(repo.login("625840","turtles"));
    }
    @Test
    public void deleteUser()
    {
        UserRepository repo = new UserRepository(new PostgresConnectionUtilities());
        repo.delete("625840");
    }

    @Test
    public void findAll()
    {
        UserRepository repo = new UserRepository(new PostgresConnectionUtilities());
        List<Users> all = repo.findAll();
        for (Users u : all)
        {
            System.out.println(u);
        }
    }

    @Test
    public void deleteAll()
    {

        UserRepository repo = new UserRepository(new PostgresConnectionUtilities());
        List<Users> all = repo.findAll();
        for (Users u : all)
        {
            System.out.println(u);
            repo.delete(u.getUsername());
        }
    }
}
