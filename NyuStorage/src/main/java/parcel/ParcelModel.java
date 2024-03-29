package parcel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import courier.Courier;

public class ParcelModel {
    private Statement statement;
    private PreparedStatement ps;
    private Connection connection;

    public ParcelModel() {
        super();
    }

    public void initJDBC() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver loaded");

        // Connect to the database
        connection = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "");
        System.out.println("Database connected");

        // Create a statement
        statement = connection.createStatement();
    }

    public void closeJDBC() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Parcel> viewParcels() throws ClassNotFoundException, SQLException {
        initJDBC();

        List<Parcel> parcelList = new ArrayList<>();

        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM parcel");

            while (rs.next()) {
                Parcel parcel = new Parcel(
                        rs.getString("tracking_num"),
                        rs.getString("receipient_name"),
                        rs.getString("tel_num"),
                        rs.getString("received_date"),
                        rs.getString("collection_date"),
                        rs.getInt("weight"),
                        rs.getString("remark"),
                        rs.getString("userId"),
                        rs.getString("shelf_id"),
                        rs.getString("courier_id")
                );

                parcelList.add(parcel);
            }
        } catch (Exception e) {
            System.out.println("viewParcels(): " + e);
            e.printStackTrace();
        } finally {
            closeJDBC();
        }

        return parcelList;
    }

    public void addParcel(Parcel parcel) throws ClassNotFoundException, SQLException {
        initJDBC();

        try {
            String sql = "INSERT INTO parcel (tracking_num, receipient_name, tel_num, received_date, collection_date, weight, remark, userId, shelf_id, courier_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            ps = connection.prepareStatement(sql);
            ps.setString(1, parcel.getTracking_num());
            ps.setString(2, parcel.getReceipient_name());
            ps.setString(3, parcel.getTel_num());
            ps.setString(4, parcel.getReceived_date()); // Use string value for received_date
            ps.setString(5, parcel.getCollection_date()); // Use string value for collection_date
            ps.setInt(6, parcel.getWeight());
            ps.setString(7, parcel.getRemark());
            ps.setString(8, parcel.getUserId());
            ps.setString(9, parcel.getShelf_id());
            ps.setString(10, parcel.getCourier_id());

            ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("addParcel(): " + ex);
            ex.printStackTrace();
        } finally {
            closeJDBC();
        }
    }

    public void updateParcel(Parcel parcel) throws SQLException, ClassNotFoundException {
        initJDBC();

        try {
            String sqlUpdate = "UPDATE parcel SET receipient_name = ?, tel_num = ?, received_date = ?, collection_date = ?, weight = ?, remark = ?, userId = ?, shelf_id = ?, courier_id = ? WHERE tracking_num = ?";

            ps = connection.prepareStatement(sqlUpdate);
            ps.setString(1, parcel.getReceipient_name());
            ps.setString(2, parcel.getTel_num());
            ps.setString(3, parcel.getReceived_date());
            ps.setString(4, parcel.getCollection_date());
            ps.setInt(5, parcel.getWeight());
            ps.setString(6, parcel.getRemark());
            ps.setString(7, parcel.getUserId());
            ps.setString(8, parcel.getShelf_id());
            ps.setString(9, parcel.getCourier_id());
            ps.setString(10, parcel.getTracking_num());

            int i = ps.executeUpdate();

            if (i > 0) {
                System.out.println("Parcel Updated Successfully");
            } else {
                System.out.println("There is a problem in updating the parcel.");
            }
        } catch (Exception ex) {
            System.out.println("updateParcel(): " + ex);
            ex.printStackTrace();
        } finally {
            closeJDBC();
        }
    }

    public Parcel getParcelByTracking_Num(String tracking_num) throws SQLException, ClassNotFoundException {
        initJDBC();

        Parcel parcel = null;

        try {
            String sqlQuery = "SELECT * FROM parcel WHERE tracking_num = ?";

            ps = connection.prepareStatement(sqlQuery);
            ps.setString(1, tracking_num);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                parcel = new Parcel(
                    rs.getString("tracking_num"),
                    rs.getString("receipient_name"),
                    rs.getString("tel_num"),
                    rs.getString("received_date"),
                    rs.getString("collection_date"),
                    rs.getInt("weight"),
                    rs.getString("remark"),
                    rs.getString("userId"),
                    rs.getString("shelf_id"),
                    rs.getString("courier_id")
                );
            }

        } catch (Exception ex) {
            System.out.println("getParcelByTracking_num(): " + ex);
            ex.printStackTrace();
        } finally {
            closeJDBC();
        }

        return parcel;
    }

    public void deleteParcelByTrackingNumber(String tracking_num) throws SQLException, ClassNotFoundException {
        initJDBC();

        try {
            String sqlDelete = "DELETE FROM parcel WHERE tracking_num = ?";

            ps = connection.prepareStatement(sqlDelete);
            ps.setString(1, tracking_num);

            ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("deleteParcelByTrackingNumber(): " + ex);
            ex.printStackTrace();
        } finally {
            closeJDBC();
        }
    }
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "");
    }
    
    public static int getTotalParcels() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int totalParcels = 0;

        try {
            conn = getConnection();
            String sql = "SELECT COUNT(tracking_num) AS total_parcels FROM parcel";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            if (rs.next()) {
                totalParcels = rs.getInt("total_parcels");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any exceptions as needed
        } finally {
            // Close the database resources
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return totalParcels;
    }
    


}
