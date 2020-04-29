package com.company.DAO.data;

import com.company.DAO.DatabaseConnection;
import com.company.DAO.models.Item;
import com.company.DAO.utils.temporaryConnection.PostgresSQLService;

import java.sql.*;


public class TemporaryServletDAO {
    private TemporaryServletDAO(){}

    DatabaseConnection databaseConnection = new DatabaseConnection();
    private static TemporaryServletDAO temporaryServletDAO;

    public static TemporaryServletDAO getInstance(){
        if(temporaryServletDAO == null)
            temporaryServletDAO = new TemporaryServletDAO();
        return temporaryServletDAO;
    }

    public int save(Item i){
        try{
            PostgresSQLService.addDBConnection(
                    "jdbc:postgresql://project0-bar-inv.ctadktwfuhte.us-west-1.rds.amazonaws.com:5432/postgres", "bar_guy", "bigpass");
            Connection connection = PostgresSQLService.getConnection(0);
            String sql = "insert into public.\"inventory\" values (DEFAULT, '"+i.getItemName()+"', '"+ i.getId()+"', '"
                    +i.getOnHand()+"', '"+i.getLowLevel()+ "', '" + i.getOptLevel() + "')";
            System.out.println(sql);
            Statement statement = connection.createStatement();
            statement.execute(sql);
            connection.close();
            System.out.println("Save statement executed");
            return 1;
        }catch(Exception ex){
            ex.printStackTrace();
            return 0;
        }
    }
}
