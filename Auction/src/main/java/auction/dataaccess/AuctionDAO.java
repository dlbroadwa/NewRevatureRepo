package auction.dataaccess;

import auction.models.Auction;
import com.sun.scenario.effect.impl.state.GaussianRenderState;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuctionDAO implements DAO<Auction, Integer> {

    private ConnectionUtils connectionUtils = null;
    private Connection connection = null;

    public AuctionDAO(ConnectionUtils connectionUtils) {
        if(connectionUtils != null) {
            this.connectionUtils = connectionUtils;
        }
    }

    @Override
    public boolean save(Auction obj) {

        String saveStatement = "INSERT INTO " + connectionUtils.getDefaultSchema() + "." + "auction"
                + " (auctionid, itemid, sellerid, enddate, startingprice, reserveprice) VALUES (?,?,?,?,?,?)";
        try {
            connection = connectionUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(saveStatement);
            preparedStatement.setInt(1, obj.getAuctionID());
            preparedStatement.setInt(2, obj.getItemID());
            preparedStatement.setInt(3, obj.getSellerID());
            preparedStatement.setDate(4, obj.getEndDate());
            preparedStatement.setBigDecimal(5, obj.getStartingPrice());
            preparedStatement.setBigDecimal(6, obj.getReservePrice());
            preparedStatement.executeUpdate();
            connection.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }return false;
    }


        public List<Auction> retrieveAll() {
            Connection connection = null;

            ArrayList<Auction> auctions = new ArrayList<>();

            try {
                connection = connectionUtils.getConnection();
                String sql = "SELECT * FROM " + connectionUtils.getDefaultSchema() + "." + "auction";
                PreparedStatement auctionStatement = connection.prepareStatement(sql);
                ResultSet resultSet = auctionStatement.executeQuery();

                while (resultSet.next()) {

                    auctions.add(new Auction(
                            resultSet.getInt("auctionid"), resultSet.getInt("itemid"), resultSet.getInt("sellerid"),
                            resultSet.getDate("enddate"), resultSet.getBigDecimal("startingprice"), resultSet.getBigDecimal("reserveprice")
                    ));

                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return auctions;
        }


        @Override
        public Auction retrieveByID(Integer integer) {
            Auction auction=null;
            try{
                connection = connectionUtils.getConnection();
                String selectStatement = "SELECT * FROM " + connectionUtils.getDefaultSchema() + "." + "auction"
                        + " WHERE  auctionid = " + integer;
                PreparedStatement preparedStatement = connection.prepareStatement(selectStatement);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (integer == resultSet.getInt(1)) {

                    auction.setAuctionID(resultSet.getInt("auctionid"));
                    auction.setItemID(resultSet.getInt("itemid"));
                    auction.setSellerID(resultSet.getInt("sellerid"));
                    auction.setEndDate(resultSet.getDate("enddate"));
                    auction.setStartingPrice(resultSet.getBigDecimal("startingprice"));
                    auction.setReservePrice(resultSet.getBigDecimal("reserveprice"));
                    connection.close();
                }
                else
                    System.out.println("No user by that id found.");

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return auction;
        }

        @Override
        public boolean delete(Auction obj) {
            String deleteStatement = "DELETE FROM " + connectionUtils.getDefaultSchema() + "." + "auction"
                    + " WHERE auctionid = ?";
            try {
                connection = connectionUtils.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(deleteStatement);
                preparedStatement.setInt(1, obj.getAuctionID());
                preparedStatement.executeUpdate();
                connection.close();
                return true;

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        public boolean update(Auction obj) {
                try{
                    connection = connectionUtils.getConnection();
                    String updateStatement = "UPDATE " + connectionUtils.getDefaultSchema() + " SET auctionid = ? AND itemid = ? AND sellerid = ? AND" +
                            " enddate = ? AND startingprice = ? AND reserveprice = ?";
                    PreparedStatement preparedStatement = connection.prepareStatement(updateStatement);

                    preparedStatement.setInt(1,obj.getAuctionID());
                    preparedStatement.setInt(2,obj.getItemID());
                    preparedStatement.setInt(3,obj.getSellerID());
                    preparedStatement.setDate(4,obj.getEndDate());
                    preparedStatement.setBigDecimal(5,obj.getStartingPrice());
                    preparedStatement.setBigDecimal(6,obj.getReservePrice());
                    preparedStatement.executeUpdate();
                    connection.close();
                    return true;


                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return false;
        }
    }
