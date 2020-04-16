package com.Project0.dao;

import com.Project0.model.Golfer;
import com.Project0.model.MatchScore;
import com.Project0.util.ConnectionUtil;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GolferDAOImpl_DB implements GolferDAO{

    private ConnectionUtil connectionUtil;

    public GolferDAOImpl_DB(ConnectionUtil connectionUtil) {
        this.connectionUtil = connectionUtil;
    }

    @Override
    public Boolean createGolfer(Golfer golfer) throws Exception {
        Connection con = null;
        PreparedStatement stmt = null;
        String schemaName = connectionUtil.getDefaultSchema();
        boolean success = false;

        try {
            con = connectionUtil.getConnection();
            if (con != null) {
                String sql = "INSERT INTO public.golfers (name, phone, emergencyphone, carmake, carmodel, licenseplate, address) VALUES (?, ?, ?, ?, ?, ?, ?)";
                stmt = con.prepareStatement(sql);
//                stmt.setString(1, connectionUtil.getDefaultSchema());
                stmt.setString(1, golfer.getName());
                stmt.setLong(2, Long.parseLong(golfer.getPhone()));
                stmt.setLong(3, Long.parseLong(golfer.getEmergencyPhone()));
                stmt.setString(4, golfer.getCarMake());
                stmt.setString(5, golfer.getCarModel());
                stmt.setString(6, golfer.getCarLicensePlate());
                stmt.setString(7, golfer.getAddress());

                //send to DB & apply result
                success = stmt.executeUpdate() > 0;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            return success;
        }
    }

    @Override
    public void updateGolferInfo(Golfer oldGolfer, Golfer newGolfer) throws Exception {
        Connection con = null;
        PreparedStatement stmt = null;
        String schemaName = connectionUtil.getDefaultSchema();

        try {
            con = connectionUtil.getConnection();
            if (con != null) {
                String sql = "UPDATE public.golfers SET name=?, address=?, phone=?, emergencyphone=?, carmake=?, carmodel=?, licenseplate=? WHERE name = ?";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, newGolfer.getName());
                stmt.setString(2, newGolfer.getAddress());
                stmt.setString(3, newGolfer.getPhone());
                stmt.setString(4, newGolfer.getEmergencyPhone());
                stmt.setString(5, newGolfer.getCarMake());
                stmt.setString(6, newGolfer.getCarModel());
                stmt.setString(7, newGolfer.getCarLicensePlate());
                stmt.setString(8, oldGolfer.getName());

                System.out.printf("SQL STATEMENT: %s \n", stmt.toString());
                if(stmt.executeUpdate() <= 0)
                    System.out.println("ERROR UPDATING INFO");
            }
        } catch (NumberFormatException | SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public ArrayList<Golfer> viewGolferInfo(Golfer golfer) {
        Connection con = null;
        PreparedStatement stmt = null;
        String schemaName = connectionUtil.getDefaultSchema();
        ArrayList<Golfer> golfers = new ArrayList<>();

//        System.out.printf("DAOIMPL - GolferPassed: %s", golfer.getName());
        try {
            con = connectionUtil.getConnection();
            if (con != null) {
                String sql = "SELECT * FROM public.golfers WHERE name LIKE ?";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, golfer.getName() + "%");

//                System.out.printf("SQL STATEMENT: %s \n", stmt.toString());
                stmt.executeQuery();
                ResultSet rs = stmt.getResultSet();

                while(rs.next()) {
                    Golfer temp = new Golfer(
                            Long.valueOf(rs.getInt("id")),
                            rs.getString("name"),
                            rs.getString("address"),
                            rs.getString("phone"),
                            rs.getString("emergencyphone"),
                            rs.getString("carmake"),
                            rs.getString("carmodel"),
                            rs.getString("licenseplate")
                    );
                    if(temp == null)
                        continue;
                    golfers.add(temp);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            return golfers;
        }
    }

    @Override
    public void addScoreToHistory(Golfer golfer, MatchScore score) throws Exception {

    }

    @Override
    public ArrayList<MatchScore> getGolferScores(Golfer golfer) {
        return null;
    }
}
