package auction.dataaccess;

import auction.models.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

    public class ItemDAO implements DAO<Item, Integer>{

        private ConnectionUtils connectionUtils = null;
        private Connection connection = null;

        public ItemDAO(ConnectionUtils connectionUtils) {
            if(connectionUtils != null) {
                this.connectionUtils = connectionUtils;
            }
        }

        @Override
        public boolean save(Item item) {
            connection = null;

            String saveStatement = "INSERT INTO " + connectionUtils.getDefaultSchema() + "." + "item"
                    + " (itemname, itemdescription) VALUES (?,?)";
            try {
                connection = connectionUtils.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(saveStatement);
                preparedStatement.setString(1, item.getName());
                preparedStatement.setString(2, item.getDescription());
                preparedStatement.executeUpdate();
                connection.close();
                return true;

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        public List<Item> retrieveAll() {
            connection = null;
            List<Item> itemList = new ArrayList<>();
            try {
                connection = connectionUtils.getConnection();
                String selectStatement = "SELECT * FROM " + connectionUtils.getDefaultSchema() + "." + "item";
                PreparedStatement preparedStatement = connection.prepareStatement(selectStatement);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    itemList.add(new Item(resultSet.getString("itemName"), resultSet.getString("itemDescription")));
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return itemList;
        }

        @Override
        public Item retrieveByID(Integer id) {

            connection = null;
            Item item = null;
            try{
                connection = connectionUtils.getConnection();
                String selectStatement = "SELECT * FROM " + connectionUtils.getDefaultSchema() + "." + "item"
                        + " WHERE  itemname = " + id;
                PreparedStatement preparedStatement = connection.prepareStatement(selectStatement);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (id == resultSet.getInt(0)) {
                    item.setName(resultSet.getString("itemName"));
                    item.setDescription(resultSet.getString("itemDescription"));

                }
                else
                    System.out.println("No user by that id found.");

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return item;

        }

        @Override
        public boolean delete(Item item) {
            connection = null;
            String deleteStatement = "DELETE FROM " + connectionUtils.getDefaultSchema() + "." + "item"
                    + " WHERE itemname = ?";
            try {
                connection = connectionUtils.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(deleteStatement);
                preparedStatement.setString(1, item.getName());
                preparedStatement.executeUpdate();
                connection.close();
                return true;

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;

        }

        @Override
        public boolean update(Item newObj) {
            Connection connection = null;
            String updateStatement = "UPDATE " + connectionUtils.getDefaultSchema()
                    + " SET itemname = ? AND itemdescription = ?";
            try
            {
                connection = connectionUtils.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(updateStatement);

                preparedStatement.setString(1, newObj.getName());
                preparedStatement.setString(2, newObj.getDescription());
                preparedStatement.executeUpdate();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }

    }
