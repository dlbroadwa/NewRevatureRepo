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
    private String currentStock;

    public Database() throws SQLException
    {
        this.relocation = Relocate();
        String username = System.getenv("user_creds");
        String password = System.getenv("user_password");
        String url = System.getenv("url");
        this.connectionUtils = new PostgresConnectionUtil
                (
                        url,
                        username,
                        password,
                        "public",
                        this.relocation
                );
        functions();
    }

    public Database(Guest guest) throws SQLException {
        this.relocation = Relocate();
        String username = System.getenv("user_creds");
        String password = System.getenv("user_password");
        String url = System.getenv("url");
        this.connectionUtils = new PostgresConnectionUtil
                (
                        url,
                        username,
                        password,
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
            this.currentStock = choice;
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

        System.out.println("The total value of all stocked " + (this.currentStock.substring(0,1).toUpperCase() + this.currentStock.substring(1)) + ": $" + totalValueOfAllInstruments + "\n");
    }

    public void readStock(ConnectionUtils connectionUtils, Guest guest) throws SQLException
    {
        this.instrumentRepos = new InstrumentSQLRepository(connectionUtils);
        this.service = new InstrumentService(instrumentRepos);
        this.allInstruments = service.getAllInstruments();
        System.out.println("\nCurrent Stock:==================================================================");
        for(InstrumentModel i : allInstruments)
        {
//            System.out.println("Id: " + i.getId() +
//                    " \nModel: " + i.getInstrumentName() +
//                    " \nUsed: " + i.getUsed() +
//                    " \nPrice: " + i.getPrice() + "\n"+
//                    "================================================================================");
            System.out.println("================================================================================");
            System.out.println("ID#: " + i.getId() + "\nBrand Name: " + i.getInstrumentName() + "\nUsed? [(0: new) (1: used) (Other: repair identification) #]\n" + i.getUsed() + "\nPrice: " + i.getPrice());
        }
    }

    public void findByIdInStock(ConnectionUtils connectionUtils) throws SQLException
    {
        this.instrumentRepos = new InstrumentSQLRepository(connectionUtils);
        this.service = new InstrumentService(instrumentRepos);
        System.out.println("Enter the id of the instrument to look up: ");
        Scanner scanner = super.getScanner();
        int choice = scanner.nextInt();
        this.idInstrument = service.findByID(choice);
        System.out.println("Id: " + this.idInstrument.getId() + "\n" +
                            "Model: " + this.idInstrument.getInstrumentName() + "\n" +
                            "Used: " + this.idInstrument.getUsed() + "\n" +
                            "Price: " + this.idInstrument.getPrice());
    }

    private void addToStock(ConnectionUtils connectionUtils) throws SQLException {
        this.instrumentRepos = new InstrumentSQLRepository(connectionUtils);
        this.service = new InstrumentService(instrumentRepos);
        service.addNewInstrument();
        System.out.println("Instrument has been added!");
        readStock(connectionUtils);
    }

    private void deleteFromStock(ConnectionUtils connectionUtils) throws SQLException {
        readStock(connectionUtils);
        this.instrumentRepos = new InstrumentSQLRepository(connectionUtils);
        this.service = new InstrumentService(instrumentRepos);
        service.removeInstrument();
        readStock(connectionUtils);
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
