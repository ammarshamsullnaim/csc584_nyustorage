package courier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import login.User;

public class CourierModel {
    private Statement statement;
    private PreparedStatement ps;
    private Connection connection;

    public CourierModel() {
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
    public boolean doesCourierExist(String courierId) throws SQLException, ClassNotFoundException {
        initJDBC();

        boolean exists = false;

        try {
            String sqlQuery = "SELECT COUNT(*) AS count FROM courier WHERE courier_id = ?";

            ps = connection.prepareStatement(sqlQuery);
            ps.setString(1, courierId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int count = rs.getInt("count");
                exists = count > 0;
            }
        } catch (Exception ex) {
            System.out.println("doesCourierExist(): " + ex);
            ex.printStackTrace();
        } finally {
            closeJDBC();
        }

        return exists;
    }

    public List<Courier> viewCourier() throws ClassNotFoundException, SQLException {
        initJDBC();

        List<Courier> courierList = new ArrayList<>();

        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM courier");

            while (rs.next()) {
                Courier courier = new Courier(
                        rs.getString("courier_id"),
                        rs.getString("courier_name")
                        
                );

                courierList.add(courier);
            }
        } catch (Exception e) {
            System.out.println("viewCourier(): " + e);
            e.printStackTrace();
        } finally {
            closeJDBC();
        }

        return courierList;
    }

    public void addCourier(Courier courier) throws ClassNotFoundException, SQLException {
        initJDBC();

        try {
            String sql = "INSERT INTO courier (courier_id, courier_name) VALUES (?, ?)";

            ps = connection.prepareStatement(sql);
            ps.setString(1, courier.getCourier_ID());
            ps.setString(2, courier.getcourier_name());

            ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("addCourier(): " + ex);
            ex.printStackTrace();
        } finally {
            closeJDBC();
        }

    }
    public void updateCourier(Courier courier) throws SQLException, ClassNotFoundException {
        initJDBC();

        try {
            String sqlUpdate = "UPDATE courier SET courier_name = ? WHERE courier_id = ?";

            ps = connection.prepareStatement(sqlUpdate);
            ps.setString(1, courier.getcourier_name());
            ps.setString(2, courier.getCourier_ID());

            int i = ps.executeUpdate();

            if (i > 0) {
                System.out.println("Record Updated Successfully");
            } else {
                System.out.println("There is a problem in updating the record.");
            }
        } catch (Exception ex) {
            System.out.println("updateCourier(): " + ex);
            ex.printStackTrace();
        } finally {
            closeJDBC();
        }
    }
    public boolean courierExists(String courier_id) throws ClassNotFoundException, SQLException {
        initJDBC();

        try {
            String sqlQuery = "SELECT COUNT(*) FROM courier WHERE courier_id = ?";
            PreparedStatement ps = connection.prepareStatement(sqlQuery);
            ps.setString(1, courier_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException ex) {
            System.out.println("courierExists(): " + ex);
            ex.printStackTrace();
        } finally {
            connection.close();
        }

        return false;
    }



    public Courier getCourierByID(String courier_id) throws SQLException, ClassNotFoundException {
        initJDBC();

        Courier courier = null;

        try {
            String sqlQuery = "SELECT * FROM courier WHERE courier_id = ?";

            ps = connection.prepareStatement(sqlQuery);
            ps.setString(1, courier_id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                courier = new Courier(
                        rs.getString("courier_id"),
                        rs.getString("courier_name")
                );
            }
        } catch (Exception ex) {
            System.out.println("getCourierByID(): " + ex);
            ex.printStackTrace();
        } finally {
            closeJDBC();
        }

        return courier;
    }

    public void deleteCourierByID(String courier_id) throws SQLException, ClassNotFoundException {
        initJDBC();

        try {
            String sqlDelete = "DELETE FROM courier WHERE courier_id = ?";

            ps = connection.prepareStatement(sqlDelete);
            ps.setString(1, courier_id);

            ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("deleteCourierByID(): " + ex);
            ex.printStackTrace();
        } finally {
            closeJDBC();
        }
    }
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "");
    }

    public static int getTotalCouriers() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int totalCouriers = 0;

        try {
            conn = getConnection();
            String sql = "SELECT COUNT(courier_id) AS total_couriers FROM courier";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            if (rs.next()) {
                totalCouriers = rs.getInt("total_couriers");
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

        return totalCouriers;
    }
    

    public List<Courier> getAllCouriers() {
        List<Courier> courierList = new ArrayList<>();

        try {
            Connection conn = getConnection();
            String sql = "SELECT * FROM courier";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String courierId = rs.getString("courier_id");
                String courierName = rs.getString("courier_name");
                // Add more properties as needed...

                Courier courier = new Courier(courierId, courierName /*, ...*/);
                courierList.add(courier);
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any exceptions as needed
        }

        return courierList;
    }

}


