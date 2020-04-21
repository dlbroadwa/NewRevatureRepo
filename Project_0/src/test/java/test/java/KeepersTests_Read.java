package test.java;

import com.ex.DAO.DAO;
import com.ex.Objects.Keepers;
import com.ex.DAO.PostgresConnectionUtil;
import com.ex.DAO.SqlDatabaseKeepers;
import com.ex.services.KeepersService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class KeepersTests_Read {

        String url,username,password,schema;

        KeepersService keepersService;
        DAO keepersDAO;
        PostgresConnectionUtil postgresConnect;

        @Before
        public void init() {
            url = System.getenv("PROJECT_0_URL");
            username = System.getenv("PROJECT_0_USERNAME");
            password = System.getenv("PROJECT_0_PASSWORD");
            schema = System.getenv("PROJECT_0_SCHEMA");
            postgresConnect = new PostgresConnectionUtil(url, username, password, schema);
            keepersDAO = new SqlDatabaseKeepers(postgresConnect);
            keepersService = new KeepersService(keepersDAO);
        }

        @Test
        public void findAll() {
            List<Keepers> keepers = new ArrayList<>();

            keepers = keepersDAO.findAll();
            for(Keepers e : keepers)
                System.out.println(e.toStringKeepers());

        }

        @Test
        public void specificFind(){
            List<Keepers> keepers = new ArrayList<>();

            keepers = keepersDAO.specificFind();
            for(Keepers e: keepers)
                System.out.println(e.toStringTrans());
        }
    }

