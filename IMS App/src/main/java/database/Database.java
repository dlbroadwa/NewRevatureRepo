package database;

import app.Application;
import clients.InstrumentService;
import data.InstrumentSQLRepository;
import data.Repository;
import models.InstrumentModel;
import utils.ConnectionUtils;
import utils.PostgresConnectionUtil;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Database extends Application
{
    public Database() throws SQLException
    {
        System.out.println("Which stock would you like to search through?\n[woodwinds, brass, strings]");
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
        ConnectionUtils connectionUtils = new PostgresConnectionUtil
                (
                        "jdbc:postgresql://database-1.cis8fsnxixal.us-east-1.rds.amazonaws.com:5432/myDatabase",
                        "jpragasa",
                        "Lucario11495",
                        "public",
                        choice
                );
        Repository<InstrumentModel, Integer> instrumentRepos = new InstrumentSQLRepository(connectionUtils);
        InstrumentService service = new InstrumentService(instrumentRepos);
        List<InstrumentModel> allInstruments = service.getAllInstruments();
        System.out.println("Current Stock: ");
        for(InstrumentModel i : allInstruments)
        {
            System.out.println("Id: " + i.getId() +
                    " \nModel: " + i.getInstrumentName() +
                    " \nUsed: " + i.getUsed() +
                    " \nPrice: " + i.getPrice() + "\n");
        }
    }
}
