/**
 * The Database Class accesses the database directly. What is being accessed in the database
 * is dependent on the user input. Based on the user input, the specific function will run and
 * access the appropriate data.
 *
 * functions:
 * relocate(): prompts the user to decide which instrument stock to be accessed
 *
 * readStock(): Connects to the database and prints all the instruments into the
 * console. It also prints out the price value of all the instruments added together.
 * @params takes in a ConnectionUtils Object to get the connection to the database.
 *
 * findIdByStock(): Connects to the database and attempts to find an instrument with
 * the specified id.
 * @params takes in a ConnectionUtils Object to get the connection to the database
 *
 * addToStock(): Connects to the database and adds a new instrument into the database.
 * @params takes in a ConnectionUtils Object to get the connection to the database.
 *
 * deleteFromStock(): Connects to the database and removes an instrument with the specified id
 * @params takes in a ConnectionUtils Object to get the connection to the database.
 *
 * functions(): prompts the user to view, add, or remove from the instrument stock. If an invalid
 * entry is input, the system exits.
 */
package database;

// Imports the necessary classes to handle queries, the InstrumentModel objects, user input and sql exceptions.
import app.Application;
import clients.InstrumentService;
import data.InstrumentSQLRepository;
import data.Repository;
import guest.Guest;
import models.InstrumentModel;
import utils.ConnectionUtils;
import utils.PostgresConnectionUtil;
import java.sql.SQLException;
import java.text.DecimalFormat;
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
    private DecimalFormat df = new DecimalFormat("0.00");

    // The Constructor sets the connection properties for Admin functionality.
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


    // Sets the connection properties for Guest functionality.
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



    // Determines which stock the Admin or Guest could look through.
    private String Relocate()
    {
        System.out.println("================================================================================");
        System.out.println("            Which stock would you like to look through today?\n                     [woodwinds, brass, strings, exit]");
        System.out.println("================================================================================\n");
        String[] choices = {"woodwinds", "brass", "strings"};
        Scanner scanner = super.getScanner();
        String choice = scanner.next().toLowerCase();
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



    // Gets the instruments from the database and prints them to the console.
    public void readStock(ConnectionUtils connectionUtils) throws SQLException
    {
        this.instrumentRepos = new InstrumentSQLRepository(connectionUtils);
        this.service = new InstrumentService(instrumentRepos);
        this.allInstruments = service.getAllInstruments();
        double totalValueOfAllInstruments = 0;
        System.out.println("\nCurrent Stock:==================================================================");
        for(InstrumentModel i : allInstruments)
        {
            System.out.println("================================================================================\n");
            System.out.println("ID#: " + i.getId() + "\nBrand Name: " + i.getInstrumentName() +
                    "\nUsed? [(0: new) (1: used) (Other: repair identification #)]\n" +
                    i.getUsed() +
                    "\nPrice: $" + i.getPrice());
            totalValueOfAllInstruments += i.getPrice();
        }

        System.out.println("\nThe total value of all stocked " + (this.currentStock.substring(0,1).toUpperCase() +
                            this.currentStock.substring(1)) +
                            ": $" + df.format(totalValueOfAllInstruments) + "\n");
    }


    // Gets the instruments from the database and prints them to the console,
    // This is an overloaded function that is used for handling guest functionality
    public void readStock(ConnectionUtils connectionUtils, Guest guest) throws SQLException
    {
        this.instrumentRepos = new InstrumentSQLRepository(connectionUtils);
        this.service = new InstrumentService(instrumentRepos);
        this.allInstruments = service.getAllInstruments();
        System.out.println("\nCurrent Stock:==================================================================");
        for(InstrumentModel i : allInstruments)
        {
            System.out.println("================================================================================");
            System.out.println("ID#: " + i.getId() + "\nBrand Name: " + i.getInstrumentName() +
                               "\nUsed? [(0: new) (1: used) (Other: repair identification #)]\n" + i.getUsed() +
                               "\nPrice: $" + i.getPrice());
        }
    }


    // Finds an instrument in the database based on the specified id.
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
                            "Price: $" + this.idInstrument.getPrice());
    }


    // Adds an Instrument to the database which lists its different properties within the sql table.
    private void addToStock(ConnectionUtils connectionUtils) throws SQLException
    {
        System.out.println("Adding an instrument with a similar id will not be added.");
        readStock(connectionUtils);
        this.instrumentRepos = new InstrumentSQLRepository(connectionUtils);
        this.service = new InstrumentService(instrumentRepos);
        service.addNewInstrument();
        readStock(connectionUtils);
    }


    // Removes an Instrument from the database.
    private void deleteFromStock(ConnectionUtils connectionUtils) throws SQLException {
        readStock(connectionUtils);
        this.instrumentRepos = new InstrumentSQLRepository(connectionUtils);
        this.service = new InstrumentService(instrumentRepos);
        service.removeInstrument();
        readStock(connectionUtils);
    }


    // Determines which Admin functionality to perform.
    private void functions() throws SQLException
    {
        System.out.println("Admin Functions:\n[view] stock\n[add] to the stock\n[find] instrument by id\n[remove] from the stock\n[exit]\n");
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
