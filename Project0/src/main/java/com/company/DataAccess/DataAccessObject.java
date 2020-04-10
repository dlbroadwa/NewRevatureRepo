package com.company.DataAccess;

public abstract class DataAccessObject {

    // Find user in the database given username and password
    public abstract void findUser(String userName, String passWord);

    public abstract void deleteUser();

    public abstract void addUser();

    public abstract void editUser();

}
