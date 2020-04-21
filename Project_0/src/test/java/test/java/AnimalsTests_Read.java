package test.java;

import com.ex.DAO.*;
import com.ex.Objects.Animals;
import com.ex.services.AnimalService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class AnimalsTests_Read {

    String url,username,password,schema;

    AnimalService animalService;
    DAO animalsDAO;
    PostgresConnectionUtil postgresConnect;

    @Before
    public void init() {
        url = System.getenv("PROJECT_0_URL");
        username = System.getenv("PROJECT_0_USERNAME");
        password = System.getenv("PROJECT_0_PASSWORD");
        schema = System.getenv("PROJECT_0_SCHEMA");
        postgresConnect = new PostgresConnectionUtil(url, username, password, schema);
        animalsDAO = new SqlDatabaseAnimals(postgresConnect);
        animalService = new AnimalService(animalsDAO);
    }

    @Test
    public void findAll() {
        List<Animals> animals = new ArrayList<>();

        animals = animalsDAO.findAll();
        for(Animals e : animals)
            System.out.println(e.toStringAll());

    }

    @Test
    public void specificFind(){
        List<Animals> animals = new ArrayList<>();

        animals = animalsDAO.specificFind();
        for(Animals e: animals)
            System.out.println(e.toStringSpecific());
    }
}
