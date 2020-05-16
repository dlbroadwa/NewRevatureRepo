/**
 * The InstrumentServiceTests Class tests the InstrumentService.
 * It mocks the repository to ensure that the acutal data within the database
 * is not harmed.
 *
 * functions:
 *
 * findById(): mocks the repository, tests if it returns the appropriate InstrumentModels.
 *
 * findAll(): mocks the repository, tests if it returns the appropriate List of InstrumentModels.
 */
package clients;

import data.Repository;
import models.InstrumentModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import java.sql.SQLException;
import java.util.List;


public class InstrumentServiceTests {

    @Mock
    Repository<InstrumentModel, Integer> repo;
    List<InstrumentModel> instruments;
    InstrumentService service;
    InstrumentModel model;

    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();


    // Sets the instrument properties.
    @Before
    public void init()
    {
        service = new InstrumentService(repo);
        this.model = new InstrumentModel();
        this.model.setId(1);
        this.model.setInstrumentName("Yamaha");
        this.model.setUsed(0);
        this.model.setPrice(2000f);
    }


    // Tests if it returns the specified InstrumentModel Object.
    @Test public void findByIdTest()
    {
        Mockito.when(repo.findById(1)).thenReturn(model);
        InstrumentModel actualModel = service.findByID(1);
        Assert.assertEquals(this.model, actualModel);
        // assert that service return the correct model
    }


    // Tests if it returns the List of InstrumentModel Objects.
    @Test public void findAllTest() throws SQLException
    {
        Mockito.when(repo.findAll()).thenReturn(instruments);
        List<InstrumentModel> actualInstrumentList = service.getAllInstruments();
        Assert.assertEquals(instruments, actualInstrumentList);
    }

}
