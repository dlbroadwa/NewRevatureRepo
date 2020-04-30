package com.company.DAO.data.web;

        import com.company.DAO.DatabaseConnection;
        import com.company.DAO.models.Item;
        import com.company.DAO.utils.servletsConnection.PostgresSQLService;

        import java.sql.*;
        import java.util.ArrayList;
        import java.util.List;

public class ItemsDAO {
    private ItemsDAO(){}
    DatabaseConnection databaseConnection = new DatabaseConnection();
    private static ItemsDAO itemsDAO;
    public static ItemsDAO getInstance(){
        if(itemsDAO == null)
            itemsDAO = new ItemsDAO();
        return itemsDAO;
    }
    public static Connection getConnection() throws SQLException {
        PostgresSQLService.addDBConnection(
                "jdbc:postgresql://project0-bar-inv.ctadktwfuhte.us-west-1.rds.amazonaws.com:5432/postgres", "bar_guy", "bigpass");
        Connection connection = PostgresSQLService.getConnection(0);
        return connection;
    }


    public static int save(Item i){
        try{
            Connection connection = ItemsDAO.getConnection();
            String sql = "insert into public.inventory(itemname,id,onhand,lowlevel,optlevel) values (?,?,?,?,?)";
            System.out.println(sql);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,i.getItemName());
            ps.setInt(2,i.getId());
            ps.setInt(3,i.getOnHand());
            ps.setInt(4,i.getLowLevel());
            ps.setInt(5,i.getOptLevel());
            ps.executeQuery();

            connection.close();
            System.out.println("Save statement executed");
            return 1;
        }catch(Exception ex){
            ex.printStackTrace();
            return 0;
        }
    }

    public static List<Item> compareColumns(String column1, String column2, String comparer){
        Connection conn = null;
        List<Item> low = new ArrayList(); //define a list of items, which will be returned
        try{
            conn = ItemsDAO.getConnection();
            // select all the rows in inventory that have the relationship we define
            String sql = "select * from public.inventory where "+column1 + comparer + column2;
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                Item tmp = new Item();
                String name =rs.getString("itemname");
                tmp.setItemName(name);
                int id = rs.getInt("id");
                tmp.setId(id);
                int onhand = rs.getInt("onhand");
                tmp.setOnHand(onhand);
                int lowLevel = rs.getInt("lowlevel");
                tmp.setLowLevel(lowLevel);
                int optLevel = rs.getInt("optlevel");
                tmp.setOptLevel(optLevel);

                low.add(tmp); //fill the list with the objects
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
        return low; //give that list
    }

    public static Item findById(Integer id){
        Item i = new Item();
        Connection conn = null;
        try{
            conn = ItemsDAO.getConnection();
            String sql ="Select * from public.inventory where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            i.setId(id);
            i.setItemName(rs.getString("itemname"));
            i.setOnHand(rs.getInt("onhand"));
            i.setLowLevel(rs.getInt("lowlevel"));
            i.setOptLevel(rs.getInt("optlevel"));

        }catch (SQLException e) {
            e.printStackTrace();
        } finally{
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return i;
    }

    public static List<Item> findAll(){
        Connection connection = null;
        List<Item> inventory = new ArrayList();
        try{
            connection = ItemsDAO.getConnection();
            String sqlQuery = "Select * from public.inventory";
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sqlQuery);

            while (rs.next()) {
                String itemName = rs.getString("itemname");
                int id = rs.getInt("id");
                int onHand = rs.getInt("onhand");
                int lowLevel = rs.getInt("lowlevel");
                int optLevel = rs.getInt("optlevel");

                Item tmp = new Item();
                tmp.setItemName(itemName);
                tmp.setId(id);
                tmp.setLowLevel(lowLevel);
                tmp.setOnHand(onHand);
                tmp.setOptLevel(optLevel);

                inventory.add(tmp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return inventory;
    }

    public static int delete(Integer integer) {
        //given an id number, remove the associated item from the table
        Connection conn = null;
        int status =0;
        try {
            conn = itemsDAO.getConnection();
            String sqlQuery = "delete from public.inventory where id=?";
            PreparedStatement ps =conn.prepareStatement(sqlQuery);
            ps.setInt(1,integer);
            status =ps.executeUpdate();

            conn.close();

        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return status;
    }

    public static int update(Item item) {
        //take the item object, and update the values
        //the id number is the constant when updating the item, so that id is used to define the entry on inventory to update
        Connection conn = null;
        int status =0;
        try {
            conn = itemsDAO.getConnection();
            String sqlQuery = "update public.inventory set itemname=?, onhand =?, lowlevel =?, optlevel =? where id=?";
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setString(1,item.getItemName());
            ps.setInt(2,item.getOnHand());
            ps.setInt(3,item.getLowLevel());
            ps.setInt(4,item.getOptLevel());
            ps.setInt(5,item.getId());
            status=ps.executeUpdate();
            conn.close();
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return status;
    }


}

