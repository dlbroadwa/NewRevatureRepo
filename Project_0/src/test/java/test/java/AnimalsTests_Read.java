package test.java;

import com.ex.DAO.*;
import com.ex.Objects.Animals;
import com.ex.services.AnimalService;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class AnimalsTests_Read {//Start of AnimalsTests_Read Class

//Instant Variables
    String url,username,password,schema;
    AnimalService animalService;
    DAO animalsDAO;
    PostgresConnectionUtil postgresConnect;

//Before
    @Before
    public void init() {//Start of init Before
        url = System.getenv("PROJECT_0_URL");
        username = System.getenv("PROJECT_0_USERNAME");
        password = System.getenv("PROJECT_0_PASSWORD");
        schema = System.getenv("PROJECT_0_SCHEMA");
        postgresConnect = new PostgresConnectionUtil(url, username, password, schema);
        animalsDAO = new SqlDatabaseAnimals(postgresConnect);
        animalService = new AnimalService(animalsDAO);
    }//End of init Before

//Tests
    @Test
    public void findAll() {//Start of findAll Test
        List<Animals> animals = new ArrayList<>();

        animals = animalsDAO.findAll();
        for(Animals e : animals)
            System.out.println(e.toStringAll());

    }//End of findAll Test

    @Test
    public void specificFind(){//Start of specificFind Test
        List<Animals> animals = new ArrayList<>();

        animals = animalsDAO.specificFind();
        for(Animals e: animals)
            System.out.println(e.toStringSpecific());
    }//End of specificFind Test

    @Test
    public void numberOf(){//Start of numberOf Test
        Integer num = new Integer(0);
        num = animalsDAO.numberOf();
        System.out.println("Number of Animals " + num);
    }//End of numberOf Test

    @Test
    public void specificNumberof(){//Start of specificNumberOf Test
        Integer num = new Integer(0);
        num = animalsDAO.specificNumberOf();
        System.out.println("Number of Species " + num);
    }//End of specificNumberOf Test
}//End of AnimalsTests_Read Class
