package com.ex.DAO;

import com.ex.Objects.Animals;
import com.ex.main.Runner;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*Class Description
    *The DAO methods are given procedures and connections to the database.
 */

public class SqlDatabaseAnimals implements DAO<Animals> {//Start of SqlDatabaseAnimals

//Instant Variables
    private Runner connectionUtils;

//Constructor
    public SqlDatabaseAnimals(Runner connectionUtils){
        if(connectionUtils != null) {
            this.connectionUtils = connectionUtils;
        }
    }

//Methods
    /*
    *FindAll Returns all the animals stored in the database. It is invoked in the Inventory Screen
    */
    public List<Animals> findAll() {//Start of findAll Method
        Connection connection = null;
        List<Animals> animals = new ArrayList<>();

        try {
            connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();

            String sql = "Select * from " + schemaName + ".animal_inventory order by type";
                Statement statement = connection.createStatement();
                    ResultSet rs = statement.executeQuery(sql);

            while(rs.next()) {
                String animalName = rs.getString("name");
                String species = rs.getString("type");
                String sex = rs.getString("gender");
                int age = rs.getInt("age");
                int enclosure = rs.getInt("enclosure");

                Animals temp = new Animals();
                    temp.setAnimalName(animalName);
                    temp.setAnimalType(species);
                    temp.setSex(sex);
                    temp.setAge(age);
                    temp.setEnclosure(enclosure);
                animals.add(temp);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return animals;
    }//End of findAll Method
    /*
    * specificFind only returns distinct(no duplicates) animal types and enclosures. It is invoked in Animal Add
    * to assist Keeper in putting like animals together
    */
    public List<Animals> specificFind() {//Start of specificFind Method
        Connection connection = null;
        List<Animals> animals = new ArrayList<>();

        try {
            connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();

            String sql = "Select distinct type, enclosure from " + schemaName + ".animal_inventory order by enclosure";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()) {
                String species = rs.getString("type");
                int enclosure = rs.getInt("enclosure");

                Animals temp = new Animals();
                temp.setAnimalType(species);
                temp.setEnclosure(enclosure);
                animals.add(temp);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return animals;
    }//End of specificFind Method
    /*
     *numberOf Returns the number(count) of animals stored in the database. It is invoked in NumberOfAnimals Screen
     */
    public Integer numberOf() {//Start of numberOf Method
        Integer number = new Integer(0);
        Connection connection = null;
        try{
            connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();

            String sql = "Select count(*) as total from " + schemaName + ".animal_inventory";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
                rs.next();
               number = rs.getInt("total");
        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            if(connection !=null){
                try{
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
        return number;
    }//End of numberOf Methods
    /*
     *specificNumberOf Returns the number(count) of species stored in the database. It is invoked in SpeciesViewing Screen
     */
    public Integer specificNumberOf(){//Start of specificNumberOf Method
        Integer number = new Integer(0);
        Connection connection = null;
        try{
            connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();

            String sql = "Select count(distinct type) as total from " + schemaName + ".animal_inventory";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            number = rs.getInt("total");
        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            if(connection !=null){
                try{
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
        return number;
    }//End of specificNumberOf Method
    /*
    * save is used to add an animal to the data base. It is invoked in AnimalAdd
    */
    public void save(Animals animal) {//Start of save Method
        Connection connection = null;
        PreparedStatement stmt = null;
        Boolean update;
        try {
            connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();

            String sql = "insert into project_0.animal_inventory (name,type,gender,age,enclosure) values (?,?,?,?,?) ";
                stmt = connection.prepareStatement(sql);
                    stmt.setString(1,animal.getAnimalName());
                    stmt.setString(2,animal.getAnimalType());
                    stmt.setString(3, animal.getSex());
                    stmt.setInt(4,animal.getAge());
                    stmt.setInt(5,animal.getEnclosure());

                update = stmt.executeUpdate() >0 ;
                    if(update){
                        System.out.println(animal.getAnimalName() + " the " + animal.getAnimalType()+" was added to the zoo!" );
                    }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }//End of save Method
    /*
    * delete is used to remove an animal from the database. It is invoked in AnimalRemove
    */
    public void delete(Animals animal) {//Start of delete Method
        Connection connection = null;
        PreparedStatement stmt = null;
        Boolean update;

        try {
            connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();

            String sql = "delete from project_0.animal_inventory where name = ? and type = ?  ";
                stmt = connection.prepareStatement(sql);
                    stmt.setString(1,animal.getAnimalName());
                    stmt.setString(2,animal.getAnimalType());
            update = stmt.executeUpdate() >0 ;
                if(update){
                    System.out.println(animal.getAnimalName() + " the " + animal.getAnimalType()+" was removed from the zoo! Goodbye "+ animal.getAnimalName());
                }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }//End of delete Method

}//End of SqlDatabaseAnimals Class



