package dev.repos;

import dev.connections.ConnectionUtil;
import dev.models.pet.Cat;
import dev.models.pet.Dog;
import dev.models.pet.Pet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *  Project 1:<br>
 * <br>
 *  The PetSQLRepository class serves to collect, manipulate and persist data brought in from a AWS RDB hosted
 *    Postgresql database used as the main method of data storage for Project 1's local shelter system.
 *  This Repository subclass is the main force behind the collection, manipulation and persisting of the database
 *    contents and will run most of the background activity as commissioned through user input in the ShelterApplication
 *     class.
 *  This will only work with Pets and integers as a primary key.
 *
 *  <br> <br>
 *  Created: <br>
 *     25 April 2020, Barthelemy Martinon<br>
 *     With assistance from: August Duet<br>
 *  Modifications: <br>
 *     25 April 2020, Barthelemy Martinon,    Created class.
 *     										  Prototyped findByID, finaAll, save and delete methods with current
 *     										    projected idea of structure based around current Pet design.
 * <br>
 *  @author Barthelemy Martinon   With assistance from: August Duet
 *  @version 25 April 2020
 */
public class PetSQLRepository implements Repository<Pet, Integer> {
    // Instance Variables
    private ConnectionUtil connectionUtil;

    // Constructor
    public PetSQLRepository(ConnectionUtil connectionUtil) {
        if(connectionUtil != null) {
            this.connectionUtil = connectionUtil;
        }
    }

    // Getter Methods

    public ConnectionUtil getConnectionUtil() { return connectionUtil; }

    // Setter Methods

    public void setConnectionUtil(ConnectionUtil connectionUtil) {
        this.connectionUtil = connectionUtil;
    }

    // Methods

    /**
     * Takes the database content, runs a hard-coded SELECT SQL query with WHERE clause to search for an entry with the
     *   specified integer for idnum. Returns null is nothing is found.
     *
     * 	@return pet Pet with target idnum (or null)
     */
    public Pet findById(Integer integer) {
        Connection connection = null;
        Pet pet = null;
        Integer targetIdNum = integer;

        try {
            connection = connectionUtil.getConnection();
            String schemaName = connectionUtil.getDefaultSchema();
            String sql = "Select * from " + schemaName + ".animals where petid=" + targetIdNum;
            PreparedStatement findByIDStatement = connection.prepareStatement(sql);
            ResultSet rs = findByIDStatement.executeQuery();

            while(rs.next()) {
                String petType = rs.getString("pettype");

                int petID = rs.getInt("petid");
                String name = rs.getString("name");
                String breed = rs.getString("breed");
                String gender = rs.getString("petgender");
                int age = rs.getInt("petage");

                if (petType.equals("dog")) {
                    pet = new Dog(petID,name,breed,gender,age);
                } else if (petType.equals("cat")) {
                    pet = new Cat(petID,name,breed,gender,age);
                }
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
        return pet;
    }

    /**
     * Takes the database content, runs a hard-coded SELECT SQL query to obtain all entries.
     * Entries are scanned for a specific value under itemType to be "translated" into an instance of the appropriate
     *   Pet subclass with the Pet information as parameter inputs.
     * All "translated" entries are put into an ArrayList of pets, which is returned to for ShelterApplication
     *   interaction.
     *
     * 	@return pets Pet ArrayList of all database rows.
     */
    public ArrayList<Pet> findAll() {
        Connection connection = null;
        ArrayList<Pet> pets = new ArrayList<Pet>();

        try {
            connection = connectionUtil.getConnection();
            String schemaName = connectionUtil.getDefaultSchema();
            String sql = "Select * from " + schemaName + ".animals order by petid";
            PreparedStatement findAllStatement = connection.prepareStatement(sql);
            ResultSet rs = findAllStatement.executeQuery();

            while(rs.next()) {
                Pet temp = null;

                String petType = rs.getString("pettype");
                int petID = rs.getInt("petid");
                String name = rs.getString("name");
                String breed = rs.getString("breed");
                String gender = rs.getString("petgender");
                int age = rs.getInt("petage");

                if (petType.equals("dog")) {
                    temp = new Dog(petID,name,breed,gender,age);
                } else if (petType.equals("cat")) {
                    temp = new Cat(petID,name,breed,gender,age);
                }
                pets.add(temp);
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
        return pets;
    }

    /**
     * Takes a newly created Pet from the ShelterApplication, and runs a hard-coded INSERT SQL statement to add the Pet
     *   into the database as a table entry.
     * Information found within the Pet given as input is collected and converted into the necessary values needed to
     *   complete the INSERT statement.
     *
     *  @param obj New Pet created by user
     *
     * 	@return statement.executeUpdate(sql) Integer value representing the amount of rows affected by the statement.
     */
    public Integer save(Pet obj) {
        Connection connection = null;

        // Extract all information from Pet instance to be stored as values for the new table entry
        // Base Item Info
        String petID = "'" + obj.getID() + "'";
        String name = "'" + obj.getName() + "'";
        String breed = "'" + obj.getBreed() + "'";
        String gender = "'" + obj.getGender() + "'";
        String age = "'" + obj.getAge() + "'";

        // Subclass Info
        String petType = null;

        if ( obj instanceof Dog ) {
            petType = "'dog'";
        } else if ( obj instanceof Cat ) {
            petType = "'cat'";
        }

        try {
            connection = connectionUtil.getConnection();
            String schemaName = connectionUtil.getDefaultSchema();
            String sql = "Insert into " + schemaName + ".animals (petid, name, petage, pettype, breed, petgender) values " +
                    "(" + petID + ", " + name + ", " + age + ", " + petType + ", " + breed + ", " + gender + ")";
            PreparedStatement saveStatement = connection.prepareStatement(sql);
            saveStatement.executeUpdate();
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
        return -1;
    }

    /**
     * Takes a Pet with updated information and an Integer that represents a target Pet's ID number and search through
     *   the animals table for a match. If one is found, the new Pet information overrides the target's information.
     *   If none are found, nothing happens.
     *
     *  @param newObj Pet to be updated
     *  @param integer Integer value representing target Pet ID
     */
    public void update(Pet newObj, Integer integer) {
        Connection connection = null;

        int targetPetID = integer;

        String name = "'" + newObj.getName() + "'";
        String breed = "'" + newObj.getBreed() + "'";
        String gender = "'" + newObj.getGender() + "'";
        String age = "'" + newObj.getAge() + "'";

        try {
            connection = connectionUtil.getConnection();
            String schemaName = connectionUtil.getDefaultSchema();
            String sql = "Update " + schemaName + ".animals set " +
                    "name=" + name + "," +
                    "breed=" + breed + "," +
                    "petgender=" + gender + "," +
                    "petage=" + age + " where petid=" + targetPetID;
            PreparedStatement updateStatement = connection.prepareStatement(sql);
            updateStatement.executeUpdate();
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
    }

    /**
     * Takes a Pet from the ShelterApplication and runs a hard-coded DELETE SQL statement to remove the entry from the
     *   database.
     *
     *  @param obj Pet to be removed
     */
    public void delete(Pet obj) {
        Connection connection = null;
        int idNum = obj.getID();

        try {
            connection = connectionUtil.getConnection();
            String schemaName = connectionUtil.getDefaultSchema();
            String sql = "delete from " + schemaName + ".animals where petid=" + idNum;
            PreparedStatement deleteStatement = connection.prepareStatement(sql);
            deleteStatement.executeUpdate();
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
    }
}
