package model;

public class Employee {

    //Instance Variables
    String lastname = null;
    int ID = 0;
    String username = null;
    String password = null;

    // Constructor
    public Employee(String lastname, int ID, String username, String password) {
        this.lastname = lastname;
        this.ID = ID;
        this.username = username;
        this.password = password;
    }

    // Getter Methods


    public String getLastname() {
        return lastname;
    }

    public int getID() {
        return ID;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    // Setter Methods

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Methods

    public boolean userAuth(String userInput, String passInput) {
        if ((this.username).equals(userInput) && (this.password).equals(passInput)) {
            return true;
        }
        return false;
    }

}

