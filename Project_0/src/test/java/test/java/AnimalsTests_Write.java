package test.java;

import com.ex.Objects.Animals;
import com.ex.DAO.DAO;
import com.ex.DAO.PostgresConnectionUtil;
import com.ex.services.AnimalService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;


public class AnimalsTests_Write {
    String url,username,password,schema;

    @InjectMocks
    AnimalService animalService;

    @Mock
    DAO animalsDAO;

    @Mock
    PostgresConnectionUtil postgresConnect;

    @Rule
    public MockitoRule mockitoRule_UDAO = MockitoJUnit.rule();

    @Before
    public void init(){
        url = System.getenv("PROJECT_0_URL");
        username= System.getenv("PROJECT_0_USERNAME");
        password= System.getenv("PROJECT_0_PASSWORD");
        schema= System.getenv("PROJECT_0_SCHEMA");
        animalService = new AnimalService(animalsDAO);
    }

    @Test
    public void save(){
        Animals temp = new Animals();
            temp.setAnimalName("Frank");
            temp.setAnimalType("Flamingo");
            temp.setSex("M");
            temp.setAge(2);
            temp.setEnclosure(5);

        Mockito.doNothing().when(animalsDAO).save(temp);

        boolean successMock = animalService.save(temp);
        Assert.assertTrue("NO SAVE HAPPENED", successMock);
    }

    @Test
    public void delete(){
        Animals temp = new Animals();
            temp.setAnimalName("Frank");
            temp.setAnimalType("Flamingo");
            temp.setSex("M");
            temp.setAge(2);
            temp.setEnclosure(5);

         Mockito.doNothing().when(animalsDAO).delete(temp);

        boolean successMock = animalService.delete(temp);
        Assert.assertTrue("NO SAVE HAPPENED", successMock);
    }

}


