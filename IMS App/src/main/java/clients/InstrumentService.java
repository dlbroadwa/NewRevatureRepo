package clients;

import app.Application;
import data.Repository;
import models.InstrumentModel;

import java.sql.SQLException;
import java.util.List;

public class InstrumentService extends Application
{
    private Repository<InstrumentModel, Integer> repo;

    public InstrumentService(Repository<InstrumentModel, Integer> repo)
    {
        this.repo = repo;
    }

    public List<InstrumentModel> getAllInstruments() throws SQLException
    {
        return this.repo.findAll();
    }

    public InstrumentModel findByID(int i)
    {
        return this.repo.findById(i);
    }

    public void addNewInstrument()
    {
        this.repo.update();
    }

    public void removeInstrument()
    {
        this.repo.delete();
    }
}
