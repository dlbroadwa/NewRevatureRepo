package com.company.DataAccess;

public interface DAO {
    public void findUser(String userName, String passWord);

    public void deleteUser();

    public void addUser();

    public void editUser();
}
