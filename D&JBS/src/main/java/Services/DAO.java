package Services;

import Controler.AccountHolderPerSonalInfo;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * this abstract class just set the rules for anyone that will want to extends it
 * it help define the logic of all abstract method that is part of it
 *
 */

public  abstract class DAO {

    ArrayList list = new ArrayList();

    public abstract void InsertMethod(AccountHolderPerSonalInfo employee) throws ClassNotFoundException;
    public abstract void UpdateMethod(String tableName ,String something,String somethingElse, int id ) throws ClassNotFoundException;
    public abstract void DeleteMethod(String tableName ,String something, int id  ) throws ClassNotFoundException;
    public abstract void SelectedAll(AccountHolderPerSonalInfo employee) throws ClassNotFoundException;

    public  abstract void InsertToRegisterTable(AccountHolderPerSonalInfo employee) throws ClassNotFoundException;
    public abstract void InsertToLoginTable(AccountHolderPerSonalInfo employee) throws ClassNotFoundException;
    public  abstract  void InsertToAccountNumberTable(AccountHolderPerSonalInfo employee) throws ClassNotFoundException;

    public abstract void UpdateToRegisterTable(AccountHolderPerSonalInfo employee, AccountHolderPerSonalInfo employee2);
    public abstract void UpdateToLoginTable(AccountHolderPerSonalInfo employee,AccountHolderPerSonalInfo employee2);
    public abstract void UpdateToAccounNumberTable(AccountHolderPerSonalInfo employee,AccountHolderPerSonalInfo employee2);

    public  abstract void SelectFromRegisterTable() throws ClassNotFoundException;
    public abstract void SelectFromLoginTable(AccountHolderPerSonalInfo employee) throws ClassNotFoundException;
    public  abstract  void SelectFromAccountNumberTable(AccountHolderPerSonalInfo employee) throws ClassNotFoundException;


    public  abstract int getRegisterId() throws ClassNotFoundException;

    public abstract Connection getTheConnection();




}