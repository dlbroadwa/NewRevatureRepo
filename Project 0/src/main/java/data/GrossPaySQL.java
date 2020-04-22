package data;

import dbutility.ConnectionDBUtility;
import models.Employee;
import models.GrossPay;
/**
 * Grosspay DAO utilizing scanner object for user input and java table for temporary storage of data. Information from the database is pulled in through the connection utility.
 * This class contains the necessary SQL functions to perform searches, add hours from Timesheet Table and calculate gross pay for the GrossPay Table.
 * This DAO is currently not being utilized. Future functionality to be added.
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GrossPaySQL implements Dao<GrossPay, String> {
    //private List<GrossPay> timesheetList;
    private ConnectionDBUtility connectionDBUtility;
    private Scanner scanner = new Scanner(System.in);

    public GrossPaySQL(ConnectionDBUtility connectionDBUtility) {
        if(connectionDBUtility != null) {
            this.connectionDBUtility = connectionDBUtility;
        }
    }

    @Override
    public GrossPay findById(GrossPay g) {
        return null;
    }

    @Override
    public List<GrossPay> findAll() {
        Connection connection = null;
        List<GrossPay> grossPays = new ArrayList<>();

        try {
            connection = connectionDBUtility.getConnection();
            String schemaName = connectionDBUtility.getDefaultSchema();
            String sql = "insert into " + schemaName + ".grosspay (totalhours) select sum(monday, tuesday, wednesday, thursday, friday, saturday, sunday) from " + schemaName + ".timesheet where userid=?";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                int grossPay =rs.getInt("grosspayid");
                String userid = rs.getString("userid");
                String totalHours = rs.getString("totalhours");
                String pay = rs.getString("pay");

                GrossPay temp = new GrossPay();
                temp.setGrossPayID(grossPay);
                temp.setUserid(userid);
                temp.setTotalHours(totalHours);
                temp.setPay(pay);
                grossPays.add(temp);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                }catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return grossPays;
    }

    @Override
    public void save(GrossPay obj) {

    }

    @Override
    public void update(GrossPay obj) {

    }

    @Override
    public void delete(GrossPay obj) {

    }
}
