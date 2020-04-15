package com.ex.dao;

import com.ex.main.Runner;

import java.lang.reflect.Array;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlDatabase implements DAO<Animals, String, String, Integer, Integer> {
    private Runner connectionUtils;

    public SqlDatabase(Runner connectionUtils){
        if(connectionUtils != null) {
            this.connectionUtils = connectionUtils;
        }
    }

    @Override
    public List<Animals> findAll() {
        Connection connection = null;
        List<Animals> animals = new ArrayList<>();

        try {
            connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String sql = "Select name,type, gender,age,enclosure from " + schemaName + ".animal_inventory order by type";
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

    @Override
    public String  save(Animals animal) {
        Connection connection = null;
        List<Animals> animals = new ArrayList<>();
        try {
            connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();

            String sql = "insert into ?.animal_inventory (name,type,gender,age,enclosure) values ('?','?','?','?','?') ";
            PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1,schemaName);
                stmt.setString(2,animal.getAnimalName());
                stmt.setString(3,animal.getAnimalType());
                stmt.setInt(4,animal.getAge());
                stmt.setInt(5,animal.getEnclosure());
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
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
        return null;
    }

    @Override
    public void update(Animals newName, String species, String sex, Integer age, Integer enclosure) {

    }

    @Override
    public void delete(Animals name) {

    }


}



