package database;

import app.Application;
import clients.InstrumentService;
import data.InstrumentSQLRepository;
import data.Repository;
import guest.Guest;
import models.InstrumentModel;
import utils.ConnectionUtils;
import utils.PostgresConnectionUtil;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Database extends Application
{
    private String relocation;
    private Repository<InstrumentModel, Integer> instrumentRepos;
    private InstrumentService service;
    private List<InstrumentModel> allInstruments;
    private ConnectionUtils connectionUtils;
    public Database() throws SQLException
    {
        this.relocation = Relocate();
        this.connectionUtils = new PostgresConnectionUtil
                (
                        "jdbc:postgresql://database-1.cis8fsnxixal.us-east-1.rds.amazonaws.com:5432/myDatabase",
                        "jpragasa",
                        "Lucario11495",
                        "public",
                        this.relocation
                );
        functions();
    }

    public Database(Guest guest) throws SQLException {
        this.relocation = Relocate();
        this.connectionUtils = new PostgresConnectionUtil
                (
                        "jdbc:postgresql://database-1.cis8fsnxixal.us-east-1.rds.amazonaws.com:5432/myDatabase",
                        "jpragasa",
                        "Lucario11495",
                        "public",
                        this.relocation
                );
        ReadStock(this.connectionUtils);
    }

    private String Relocate()
    {
        System.out.println("Which stock would you like to look through today?\n[woodwinds, brass, strings]");
        String[] choices = {"woodwinds", "brass", "strings"};
        Scanner scanner = super.getScanner();
        String choice = scanner.next();
        if(choice.contains(choices[0]) || choice.contains(choices[1]) || choice.contains(choices[2]))
        {
            //Do nothing
        }
        else
        {
            super.exitSystem();
        }
        return choice;
    }

    public void ReadStock(ConnectionUtils connectionUtils) throws SQLException
    {
        this.instrumentRepos = new InstrumentSQLRepository(connectionUtils);
        this.service = new InstrumentService(instrumentRepos);
        this.allInstruments = service.getAllInstruments();
        System.out.println("Current Stock: ");
        for(InstrumentModel i : allInstruments)
        {
            System.out.println("Id: " + i.getId() +
                    " \nModel: " + i.getInstrumentName() +
                    " \nUsed: " + i.getUsed() +
                    " \nPrice: " + i.getPrice() + "\n");
        }
    }

    private void addToStock(ConnectionUtils connectionUtils)
    {
        this.instrumentRepos = new InstrumentSQLRepository(connectionUtils);
        this.service = new InstrumentService(instrumentRepos);
        service.addNewInstrument();
    }

    private void deleteFromStock(ConnectionUtils connectionUtils)
    {
        this.instrumentRepos = new InstrumentSQLRepository(connectionUtils);
        this.service = new InstrumentService(instrumentRepos);
        service.removeInstrument();
    }

    private void functions() throws SQLException
    {
        System.out.println("Would you like to:\n[view] stock\n[add] to the stock\n[remove] from the stock\n[exit]");
        String[] choices = {"view", "add", "remove", "exit"};
        Scanner scanner = super.getScanner();
        String choice = scanner.next().toLowerCase();
        if(choice.equals(choices[0]))
        {
            ReadStock(this.connectionUtils);
        }
        else if(choice.equals(choices[1]))
        {
            addToStock(this.connectionUtils);
        }
        else if(choice.equals(choices[2]))
        {
            deleteFromStock(this.connectionUtils);
        }
        else
        {
            super.exitSystem();
        }
    }
}
