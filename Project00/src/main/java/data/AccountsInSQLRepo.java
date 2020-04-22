package data;

import clients.AccountsService;
import clients.UsersService;
import model.Accounts;
import model.Users;
import utilities.ConnectionUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class AccountsInSQLRepo implements IAccounts <Accounts, Integer > {

    private ConnectionUtils connectionUtils;
    public AccountsInSQLRepo(ConnectionUtils connectionUtils) {
        if(connectionUtils != null) {
            this.connectionUtils = connectionUtils;
        }
    }


    public void updateBalance(Accounts obj, float amount) {
        Connection conn = null;
        float newBalance = obj.getBalance() + amount;
        try {
            conn = connectionUtils.getConnection();
            String sql = "update myschema.accounts set balance = " + newBalance + " where id = " +
                    obj.getAccount_id() + ";";
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }



    }

    public Accounts findByAccount(String email) {
        Connection conn = null;
        Accounts account = new Accounts();

        Users user;
        IBankUsers<Users, String> IBankUsers = new BankUsersInSQLRepo(connectionUtils) ;
        UsersService usersService = new UsersService(IBankUsers);

        try {
            conn = connectionUtils.getConnection();
            String sql = "select * from myschema.accounts ;";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()){
                String tmpEmail = rs.getString("account_holder");
                if (tmpEmail.equals(email)){

                    account.setAccountType(rs.getString("account_type"));
                    account.setBalance(rs.getFloat("balance"));
                    account.setAccount_id(rs.getInt("id"));

                    user = usersService.findUserByEmail(tmpEmail);
                    account.setHolder(user);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return account;
    }


    public List<Accounts> findAll() {
        return null;
    }



}

