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
    private InstrumentModel idInstrument;
    private InstrumentService service;
    private List<InstrumentModel> allInstruments;
    private ConnectionUtils connectionUtils;
    private String currentstock;
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
        readStock(this.connectionUtils, guest);
    }

    private String Relocate()
    {
        System.out.println("================================================================================");
        System.out.println("            Which stock would you like to look through today?\n                     [woodwinds, brass, strings]");
        System.out.println("================================================================================");
        String[] choices = {"woodwinds", "brass", "strings"};
        Scanner scanner = super.getScanner();
        String choice = scanner.next();
        if(choice.contains(choices[0]) || choice.contains(choices[1]) || choice.contains(choices[2]))
        {
            //Do nothing
            this.currentstock = choice;
        }
        else
        {
            super.exitSystem();
        }
        return choice;
    }

    public void readStock(ConnectionUtils connectionUtils) throws SQLException
    {
        this.instrumentRepos = new InstrumentSQLRepository(connectionUtils);
        this.service = new InstrumentService(instrumentRepos);
        this.allInstruments = service.getAllInstruments();
        double totalValueOfAllInstruments = 0;
        System.out.println("Current Stock: \n" +
                           "================================================================================");
        for(InstrumentModel i : allInstruments)
        {
            System.out.println("Id: " + i.getId() +
                    " \nModel: " + i.getInstrumentName() +
                    " \nUsed: " + i.getUsed() +
                    " \nPrice: " + i.getPrice() + "\n" +
                    "================================================================================");
            totalValueOfAllInstruments += i.getPrice();
        }

        System.out.println("The total value of all stocked " + (this.currentstock.substring(0,1).toUpperCase() + this.currentstock.substring(1)) + ": $" + totalValueOfAllInstruments + "\n");
    }

    public void readStock(ConnectionUtils connectionUtils, Guest guest) throws SQLException
    {
        this.instrumentRepos = new InstrumentSQLRepository(connectionUtils);
        this.service = new InstrumentService(instrumentRepos);
        this.allInstruments = service.getAllInstruments();
        System.out.println("Current Stock:\n " +
                "================================================================================");
        for(InstrumentModel i : allInstruments)
        {
            System.out.println("Id: " + i.getId() +
                    " \nModel: " + i.getInstrumentName() +
                    " \nUsed: " + i.getUsed() +
                    " \nPrice: " + i.getPrice() + "\n"+
                    "================================================================================");
        }
    }

    public void findByIdInStock(ConnectionUtils connectionUtils) throws SQLException
    {
        this.instrumentRepos = new InstrumentSQLRepository(connectionUtils);
        this.service = new InstrumentService(instrumentRepos);
        this.idInstrument = service.findByID();
        System.out.println("Id: " + this.idInstrument.getId() + "\n" +
                            "Model: " + this.idInstrument.getInstrumentName() + "\n" +
                            "Used: " + this.idInstrument.getUsed() + "\n" +
                            "Price: " + this.idInstrument.getPrice());
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
        System.out.println("Would you like to:\n[view] stock\n[add] to the stock\n[find] instrument by id\n[remove] from the stock\n[exit]");
        String[] choices = {"view", "add", "remove","find", "exit"};
        Scanner scanner = super.getScanner();
        String choice = scanner.next().toLowerCase();
        if(choice.equals(choices[0]))
        {
            readStock(this.connectionUtils);
        }
        else if(choice.equals(choices[1]))
        {
            addToStock(this.connectionUtils);
        }
        else if(choice.equals(choices[2]))
        {
            deleteFromStock(this.connectionUtils);
        }
        else if(choice.equals(choices[3]))
        {
            findByIdInStock(this.connectionUtils);
        }
        else
        {
            super.exitSystem();
        }
    }
}
