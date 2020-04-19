package com.ex.DAO;

import com.ex.DAO.Animals;
import com.ex.DAO.DAO;
import com.ex.main.Runner;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlDatabaseAnimals implements DAO<Animals> {

//Instant Variables
    private Runner connectionUtils;

//Constructor
    public SqlDatabaseAnimals(Runner connectionUtils){
        if(connectionUtils != null) {
            this.connectionUtils = connectionUtils;
        }
    }

//Methods
    public List<Animals> findAll() {
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
    }

    public List<Animals> specificFind() {
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
    }

    public void save(Animals animal) {
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
    }

    public void delete(Animals animal) {
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
    }



}



