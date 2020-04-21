/**
 * The InstrumentService Class extends the Application abstract class and acts as a middle-man
 * between the SQL Queries and the Database Object that actually accesses the database.
 *
 * functions:
 *
 * getAllInstruments(): gets the encapsulated Repository and calls the findAll() method. Queries the database and returns
 * a list of type InstrumentModel.
 *
 * findById(): Calls the findById() method from within the encapsulated Repository and returns an.
 * object of type InstrumentModel.
 * @params int id
 *
 * addNewInstrument(): Calls the update() method from the encapsulated Repository.
 *
 * removeInstrument(): Calls the delete() method from the encapsulated Repository.
 */
package clients;

//Import appropriate classes in relation to sql query class, repository, and lists
import app.Application;
import data.Repository;
import models.InstrumentModel;
import java.sql.SQLException;
import java.util.List;

public class InstrumentService extends Application
{
    private Repository<InstrumentModel, Integer> repo;

    // sets the Repository from the SQL query
    public InstrumentService(Repository<InstrumentModel, Integer> repo)
    {
        this.repo = repo;
    }

    // Returns a list of InstrumentModel objects
    public List<InstrumentModel> getAllInstruments() throws SQLException
    {
        return this.repo.findAll();
    }

    // Returns an InstrumentModel with the inputted id integer
    public InstrumentModel findByID(int i)
    {
        return this.repo.findById(i);
    }

    // Performs the update function from the Repository interface that is connected to the Query Class
    public void addNewInstrument()
    {
        this.repo.update();
    }

    // Performs the delete function from the Repository interface that is connected to the Query Class
    public void removeInstrument()
    {
        this.repo.delete();
    }
}
