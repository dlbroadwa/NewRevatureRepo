package data;

import models.Transactions;
import utils.ConnectionUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TransRepo implements Repository<Transactions, Integer>
{


        private ConnectionUtils connectionUtils;

        public TransRepo(ConnectionUtils connectionUtils)
        {
            if (connectionUtils != null)
            {
                this.connectionUtils = connectionUtils;
            }
        }

        @Override
        // Attempts to query the database to find the instrument with the specified identification number.
        public Transactions findById(Integer i) {
            Connection connection = null;
            try {
                connection = connectionUtils.getConnection();
                String sql = String.format("select * from  Transactions where id = '%d'", i);
                Statement statement = connection.createStatement();
                statement.executeQuery(sql);
                ResultSet rs = statement.executeQuery(sql);
                Integer id = rs.getInt("trans_id");
                Integer upc = rs.getInt("upc");
                String email = rs.getString("email");
                String date = rs.getString("date");
                String time = rs.getString("time");
                Float price = rs.getFloat("price");
                return new Transactions(id, upc, email, date, time, price);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }


        // Queries the database, gets all the instruments, and stores it within the List Object.
        @Override
        public List<Transactions> findAll() {
            Connection connection = null;
            List<Transactions> transactions = new ArrayList<Transactions>();
            try {
                connection = connectionUtils.getConnection();
                String schemaName = connectionUtils.getDefaultSchema();
                String instrumentTable = connectionUtils.getInstrumentTable();
                String sql = String.format("select * from Transactions");
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(sql);
                while (rs.next()) {
                    Integer id = rs.getInt("trans_id");
                    Integer upc = rs.getInt("upc");
                    String email = rs.getString("email");
                    String date = rs.getString("date");
                    String time = rs.getString("time");
                    Float price = rs.getFloat("price");
                    transactions.add(new Transactions(id, upc, email, date, time, price));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            return transactions;
        }


        // Queries the database to allow a new instrument to be added.
        @Override
        public void update(Integer p) {

            return;
        }


        @Override
        public void save(Transactions obj) {

            Connection connection = null;
            try {
                connection = connectionUtils.getConnection();
                String sql = String.format("insert into Transactionss (trans_id, upc, " +
                                "email, date_of,time_of,price)" +
                                "values (%d, %d, '%s', now(),now(),%d)",
                        obj.getTrans_id(), obj.getUpc(), obj.getEmaill(), obj.getPrice());
                Statement statement = connection.createStatement();
                statement.executeUpdate(sql);

            } catch (SQLException e) {
                //e.printStackTrace();
                // Will throw an exception anyway due to being a void method.
            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        //e.printStackTrace();
                        // Will throw an exception anyway due to being a void method.
                    }
                }
            }
        }

        // Queries the database to allow the removal of an instrument.
        @Override //Illegal operation, no Transaction deletion allowed
        public void delete(Integer trans_id)
        {
//            Connection connection = null;
//            try {
//                connection = connectionUtils.getConnection();
//                String sql = String.format("delete from Transactions where trans_id  = '%d'", trans_id);
//                Statement statement = connection.createStatement();
//                statement.executeUpdate(sql);
//            } catch (SQLException e) {
//                //e.printStackTrace();
//                // Will throw an exception anyway due to being a void method.
//            } finally {
//                if (connection != null) {
//                    try {
//                        connection.close();
//                    } catch (SQLException e) {
//                        //e.printStackTrace();
//                        // Will throw an exception anyway due to being a void method.
//                    }
//                }
//            }
//        }
//    }
        }
}